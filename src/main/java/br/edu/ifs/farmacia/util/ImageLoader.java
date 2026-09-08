package br.edu.ifs.farmacia.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.formdev.flatlaf.extras.FlatSVGIcon;

/**
 * Utilitário profissional para carregamento e renderização de imagens e ícones no PharmaStation.
 * Implementa suporte nativo a High-DPI (BaseMultiResolutionImage) e algoritmos de downscaling
 * progressivo bicúbico com antialiasing e filtro de nitidez adaptativo.
 */
public class ImageLoader {

    private static final String PATH_ICONS = "/imagens/";
    private static final Map<String, ImageIcon> ICON_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, BufferedImage> BUFFERED_IMAGE_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, List<Image>> WINDOW_ICONS_CACHE = new ConcurrentHashMap<>();

    // Cria uma imagem de erro padrão vetorial com gradiente e antialiasing
    private static ImageIcon createErrorIcon() {
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        float[] dist = {0.0f, 0.6f, 1.0f};
        Color[] colors = {new Color(255, 99, 71), new Color(255, 69, 69), new Color(139, 0, 0)};
        RadialGradientPaint radialGradient = new RadialGradientPaint(
            size / 2.0f, size / 2.0f, size / 2.0f,
            dist, colors
        );
        g.setPaint(radialGradient);
        g.fill(new Ellipse2D.Double(0, 0, size, size));

        BufferedImage shineLayer = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gShine = shineLayer.createGraphics();
        gShine.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gShine.setColor(new Color(255, 255, 255, 60));
        gShine.fill(new Ellipse2D.Double(2, 2, size - 4, size - 4));
        gShine.dispose();

        g.setComposite(AlphaComposite.SrcOver.derive(0.6f));
        g.drawImage(shineLayer, 0, 0, null);

        g.setComposite(AlphaComposite.SrcOver.derive(1.0f));
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        int padding = 3;

        g.drawLine(padding, padding, size - padding - 1, size - padding - 1);
        g.drawLine(size - padding - 1, padding, padding, size - padding - 1);

        g.dispose();
        return new ImageIcon(image);
    }

    /**
     * Carrega a imagem original na sua resolução nativa.
     *
     * @param fileName Nome do arquivo na pasta de imagens (ex: "logo.png")
     * @return ImageIcon do arquivo ou ícone de erro se não encontrado
     */
    public static ImageIcon loadImage(String fileName) {
        String path = PATH_ICONS + fileName;
        URL resourceUrl = ImageLoader.class.getResource(path);

        if (resourceUrl == null) {
            System.err.println("Recurso não encontrado: " + path);
            return createErrorIcon();
        }
        return new ImageIcon(resourceUrl);
    }

    /**
     * Carrega e redimensiona uma imagem utilizando algoritmo progressivo multi-step bicúbico
     * combinado com BaseMultiResolutionImage para suporte total a monitores High-DPI (Retina/4K).
     *
     * @param fileName Nome do arquivo na pasta de imagens (ex: "logo.png")
     * @param width Largura lógica desejada em pixels
     * @param height Altura lógica desejada em pixels
     * @return ImageIcon em alta fidelidade com nitidez cristalina em qualquer escala de monitor
     */
    public static ImageIcon loadImage(String fileName, int width, int height) {
        String cacheKey = fileName + "@" + width + "x" + height;
        ImageIcon cached = ICON_CACHE.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        BufferedImage original = loadBufferedImage(fileName);
        if (original == null) {
            return createErrorIcon();
        }

        // Variantes de escala física para cobrir telas normais, laptops (125%/150%) e 4K (200%/300%)
        double[] scales = {1.0, 1.25, 1.5, 1.75, 2.0, 2.5, 3.0};
        List<Image> variants = new ArrayList<>();

        for (double scale : scales) {
            int w = (int) Math.round(width * scale);
            int h = (int) Math.round(height * scale);
            if (w <= original.getWidth() && h <= original.getHeight()) {
                variants.add(scaleHighQuality(original, w, h));
            }
        }

        // Garante a imagem original como variante máxima de alta densidade
        if (!variants.contains(original)) {
            variants.add(original);
        }

        BaseMultiResolutionImage multiResImage = new BaseMultiResolutionImage(0, variants.toArray(new Image[0]));
        ImageIcon icon = new ImageIcon(multiResImage);
        ICON_CACHE.put(cacheKey, icon);
        return icon;
    }

    /**
     * Carrega uma lista com múltiplos tamanhos do ícone para uso na janela do aplicativo (JFrame.setIconImages),
     * garantindo perfeita nitidez na barra de título, barra de tarefas do Windows e menu Alt+Tab.
     *
     * @param fileName Nome da imagem fonte (ex: "logo.png")
     * @return Lista de imagens em resoluções 16x16, 24x24, 32x32, 48x48, 64x64, 128x128, 256x256
     */
    public static List<Image> loadWindowIcons(String fileName) {
        List<Image> cached = WINDOW_ICONS_CACHE.get(fileName);
        if (cached != null) {
            return cached;
        }

        BufferedImage original = loadBufferedImage(fileName);
        if (original == null) {
            return Collections.emptyList();
        }

        int[] sizes = {16, 24, 32, 48, 64, 128, 256};
        List<Image> list = new ArrayList<>();
        for (int size : sizes) {
            if (size <= original.getWidth()) {
                list.add(scaleHighQuality(original, size, size));
            }
        }
        list.add(original);

        List<Image> unmodifiable = Collections.unmodifiableList(list);
        WINDOW_ICONS_CACHE.put(fileName, unmodifiable);
        return unmodifiable;
    }

    /**
     * Lê um BufferedImage diretamente dos recursos com canal ARGB seguro.
     */
    private static BufferedImage loadBufferedImage(String fileName) {
        BufferedImage cached = BUFFERED_IMAGE_CACHE.get(fileName);
        if (cached != null) {
            return cached;
        }

        String path = PATH_ICONS + fileName;
        try (InputStream in = ImageLoader.class.getResourceAsStream(path)) {
            if (in == null) {
                System.err.println("Recurso de imagem não encontrado: " + path);
                return null;
            }
            BufferedImage loaded = ImageIO.read(in);
            if (loaded == null) {
                return null;
            }

            // Converte para TYPE_INT_ARGB se necessário para garantir pipeline acelerado e canal alfa
            BufferedImage argbImage;
            if (loaded.getType() == BufferedImage.TYPE_INT_ARGB) {
                argbImage = loaded;
            } else {
                argbImage = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = argbImage.createGraphics();
                g.drawImage(loaded, 0, 0, null);
                g.dispose();
            }

            BUFFERED_IMAGE_CACHE.put(fileName, argbImage);
            return argbImage;
        } catch (Exception e) {
            System.err.println("Erro ao ler recurso de imagem " + path + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Redimensionamento de alta fidelidade com algoritmo Multi-step Progressive Scaling
     * (técnica Chris Campbell / imgscalr) com interpolação bicúbica e filtro adaptativo de nitidez.
     */
    public static BufferedImage scaleHighQuality(BufferedImage img, int targetW, int targetH) {
        int w = img.getWidth();
        int h = img.getHeight();

        // Passos intermediários sucessivos (half-stepping) para reduções superiores a 2x
        BufferedImage current = img;
        while (w > targetW * 2 || h > targetH * 2) {
            w = Math.max(targetW, w / 2);
            h = Math.max(targetH, h / 2);
            BufferedImage step = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = step.createGraphics();
            applyHighQualityHints(g2);
            g2.drawImage(current, 0, 0, w, h, null);
            g2.dispose();
            current = step;
        }

        // Passo final para a dimensão exata com interpolação bicúbica de máxima precisão
        BufferedImage finalImg = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gFinal = finalImg.createGraphics();
        applyHighQualityHints(gFinal);
        gFinal.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        gFinal.drawImage(current, 0, 0, targetW, targetH, null);
        gFinal.dispose();

        // Aplica filtro de nitidez sutil quando houve redução considerável, preservando clareza de contornos
        if (img.getWidth() >= targetW * 1.3) {
            return applySubtleSharpen(finalImg);
        }

        return finalImg;
    }

    /**
     * Configura os melhores RenderingHints possíveis para a JVM e placa gráfica.
     */
    private static void applyHighQualityHints(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    /**
     * Filtro unsharp/sharpen suave via ConvolveOp para evitar o efeito esbranquiçado ou leitoso
     * típico de interpolações lineares em reduções severas.
     */
    private static BufferedImage applySubtleSharpen(BufferedImage img) {
        try {
            float[] sharpenMatrix = {
                 0.0f, -0.06f,  0.0f,
                -0.06f, 1.24f, -0.06f,
                 0.0f, -0.06f,  0.0f
            };
            BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, sharpenMatrix), ConvolveOp.EDGE_NO_OP, null);
            BufferedImage sharpened = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            return op.filter(img, sharpened);
        } catch (Exception e) {
            // Em caso de qualquer incompatibilidade com o kernel nos cantos, retorna a imagem original
            return img;
        }
    }

    /**
     * Carrega um ícone vetorial SVG em resolução nativa sem perda de qualidade.
     *
     * @param fileName Nome do arquivo SVG dentro de /imagens/ (ex: "search.svg")
     * @param width Largura desejada em pixels
     * @param height Altura desejada em pixels
     * @return FlatSVGIcon vetorial nítido em qualquer resolução
     */
    public static FlatSVGIcon loadSvg(String fileName, int width, int height) {
        return new FlatSVGIcon("imagens/" + fileName, width, height);
    }

    /**
     * Carrega um ícone vetorial SVG em seu tamanho padrão.
     *
     * @param fileName Nome do arquivo SVG dentro de /imagens/
     * @return FlatSVGIcon vetorial
     */
    public static FlatSVGIcon loadSvg(String fileName) {
        return new FlatSVGIcon("imagens/" + fileName);
    }
}
