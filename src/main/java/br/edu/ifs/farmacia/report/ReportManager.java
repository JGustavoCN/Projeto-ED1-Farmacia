package br.edu.ifs.farmacia.report;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import raven.toast.Notifications;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportEstoque;
    private JasperReport reportVendas;
    private Image logoImage;
    private Image watermarkImage;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
        carregarImagens();
    }

    private void carregarImagens() {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("imagens/logo.png");
            if (is != null) {
                BufferedImage original = ImageIO.read(is);
                this.logoImage = original;

                // Gerar marca d'água com 8% de opacidade para fundo sutil
                BufferedImage wm = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = wm.createGraphics();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.08f));
                g2.drawImage(original, 0, 0, null);
                g2.dispose();
                this.watermarkImage = wm;
            }
        } catch (Exception e) {
            this.logoImage = null;
            this.watermarkImage = null;
        }
    }

    public void compileReport() throws JRException {
        configurarClasspathCompilador();

        InputStream estoqueStream = getReportStream("print/estoque.jrxml");
        InputStream vendasStream = getReportStream("print/vendas.jrxml");

        if (estoqueStream == null) {
            throw new JRException("Arquivo estoque.jrxml não encontrado no JAR.");
        }
        if (vendasStream == null) {
            throw new JRException("Arquivo vendas.jrxml não encontrado no JAR.");
        }

        reportEstoque = JasperCompileManager.compileReport(estoqueStream);
        reportVendas = JasperCompileManager.compileReport(vendasStream);
    }

    private void configurarClasspathCompilador() {
        try {
            StringBuilder sb = new StringBuilder();
            String existingCp = System.getProperty("java.class.path");
            if (existingCp != null && !existingCp.isEmpty()) {
                sb.append(existingCp);
            }

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            while (cl != null) {
                if (cl instanceof java.net.URLClassLoader ucl) {
                    for (java.net.URL url : ucl.getURLs()) {
                        try {
                            java.io.File file = new java.io.File(url.toURI());
                            if (file.exists()) {
                                if (sb.length() > 0) {
                                    sb.append(java.io.File.pathSeparator);
                                }
                                sb.append(file.getAbsolutePath());
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
                cl = cl.getParent();
            }

            if (sb.length() > 0) {
                String cp = sb.toString();
                System.setProperty("net.sf.jasperreports.compiler.classpath", cp);
                net.sf.jasperreports.engine.DefaultJasperReportsContext context = net.sf.jasperreports.engine.DefaultJasperReportsContext.getInstance();
                net.sf.jasperreports.engine.JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.compiler.classpath", cp);
            }
        } catch (Exception ignored) {
        }
    }

    private InputStream getReportStream(String reportPath) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(reportPath);
        if (stream == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Arquivo de relatório não encontrado: " + reportPath);
        }
        return stream;
    }

    public void printReportEstoque(ParameterReportEstoque data) throws JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalEntrada", data.getTotalEntrada());
        parameters.put("totalSaida", data.getTotalSaida());
        parameters.put("totalLucro", data.getTotalLucro());
        parameters.put("totalQuantidade", data.getTotalQuantidade());
        parameters.put("logoEmpresa", logoImage);
        parameters.put("marcaDagua", watermarkImage);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportEstoque, parameters, dataSource);
        view(print);
    }

    public void printReportVendas(ParameterReportVendas data) throws JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalVendido", data.getTotalVendido());
        parameters.put("totalQuantidade", data.getTotalQuantidade());
        parameters.put("logoEmpresa", logoImage);
        parameters.put("marcaDagua", watermarkImage);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportVendas, parameters, dataSource);
        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}