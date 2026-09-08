package br.edu.ifs.farmacia.view.component;

import br.edu.ifs.farmacia.view.swing.Button;
import br.edu.ifs.farmacia.view.swing.MyTextField;
import br.edu.ifs.farmacia.view.swing.MyPasswordField;
import br.edu.ifs.farmacia.util.ImageLoader;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class PanelLogin extends javax.swing.JLayeredPane {

    private String userName;
    private String password;
    private MyTextField txtUser;
    private MyPasswordField txtPass;

    public PanelLogin(ActionListener eventLogin) {
        initComponents();
        initInfo();
        initLogin(eventLogin);
        this.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        loginPanel.setVisible(false);
        informacaoPanel.setVisible(true);
        loginPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:5;"
                + "background:$Table.background");
        informacaoPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:5;"
                + "background:$Table.background");
    }

    private void initInfo() {
        // Layout centralizado com logo e informacoes institucionais
        informacaoPanel.setLayout(new MigLayout("wrap", "[grow, center]", "push[]10[]5[]15[]push"));

        // Logo institucional no painel de informacoes
        JLabel logoInfo = new JLabel(ImageLoader.loadImage("logo.png", 72, 72));
        informacaoPanel.add(logoInfo, "cell 0 0, align center, gapbottom 10");

        // Titulo institucional
        JLabel tituloLabel = new JLabel("PharmaStation");
        tituloLabel.setFont(new Font("sansserif", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(7, 164, 121));
        informacaoPanel.add(tituloLabel, "cell 0 1, align center");

        JLabel subtituloLabel = new JLabel("Sistema de Gestão Farmacêutica");
        subtituloLabel.setFont(new Font("sansserif", Font.BOLD, 13));
        subtituloLabel.setForeground(new Color(120, 120, 120));
        informacaoPanel.add(subtituloLabel, "cell 0 2, align center, gapbottom 10");

        // Descricao do sistema
        JLabel informacoesLabel = new JLabel(
                "<html><div style='text-align: justify;'>"
                + "O projeto implementa um sistema de gerenciamento de estoque para uma farmácia, "
                + "desenvolvido em Java com interface gráfica construída usando Swing. Utilizando a arquitetura MVC, "
                + "o sistema permite a criação e gestão de usuários com diferentes níveis de acesso, "
                + "como administradores e funcionários. As principais funcionalidades incluem o controle de estoque, "
                + "registro de vendas, e geração de relatórios. A persistência de dados é feita por meio da serialização de objetos, "
                + "garantindo que as informações sejam mantidas entre sessões sem a necessidade de um banco de dados.</div></html>");

        informacoesLabel.setFont(new Font("sansserif", Font.PLAIN, 15));
        informacoesLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        informacoesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        informacaoPanel.add(informacoesLabel, "cell 0 3, grow, align center, gapleft 30, gapright 30, gaptop 5, gapbottom 10");

        informacaoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void initLogin(ActionListener loginEvent) {
        loginPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]5[]20[]10[]10[]20[]push"));

        // Logo oficial centralizada em alta definicao com SCALE_SMOOTH
        JLabel logoLabel = new JLabel(ImageLoader.loadImage("logo.png", 90, 90));
        loginPanel.add(logoLabel, "gapbottom 5");

        // Titulo e subtitulo modernos
        JLabel label = new JLabel("PharmaStation");
        label.setFont(new Font("sansserif", Font.BOLD, 26));
        label.setForeground(new Color(7, 164, 121));
        loginPanel.add(label);

        JLabel subLabel = new JLabel("Acesse sua conta para continuar");
        subLabel.setFont(new Font("sansserif", Font.PLAIN, 12));
        subLabel.setForeground(new Color(130, 130, 130));
        loginPanel.add(subLabel, "gapbottom 10");

        txtUser = new MyTextField();
        txtUser.setPrefixIcon(ImageLoader.loadImage("user.png"));
        txtUser.setHint("Nome de usuário");
        loginPanel.add(txtUser, "w 65%");

        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(ImageLoader.loadImage("pass.png"));
        txtPass.setHint("Senha");
        loginPanel.add(txtPass, "w 65%");

        JButton cmdForget = new JButton("Esqueceu sua senha?");
        cmdForget.setForeground(new Color(120, 120, 120));
        cmdForget.setFont(new Font("sansserif", Font.PLAIN, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(cmdForget);

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setFont(new Font("sansserif", Font.BOLD, 14));
        cmd.setText("Entrar");

        // Garante a atualizacao dos valores antes de disparar o loginEvent
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = txtUser.getText().trim();
                password = String.valueOf(txtPass.getPassword()).trim();
            }
        });
        cmd.addActionListener(loginEvent);
        loginPanel.add(cmd, "w 45%, h 40");

        // Listener para submeter ao pressionar Enter nos campos
        ActionListener enterAction = (ActionEvent e) -> cmd.doClick();
        txtUser.addActionListener(enterAction);
        txtPass.addActionListener(enterAction);
    }

    public void trocarPanel(boolean show) {
        if (show) {
            informacaoPanel.setVisible(true);
            loginPanel.setVisible(false);
        } else {
            informacaoPanel.setVisible(false);
            loginPanel.setVisible(true);
        }
    }

    public String getUserName() {
        if (txtUser != null) {
            return txtUser.getText().trim();
        }
        return this.userName != null ? this.userName : "";
    }

    public String getPassword() {
        if (txtPass != null) {
            return String.valueOf(txtPass.getPassword()).trim();
        }
        return this.password != null ? this.password : "";
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        informacaoPanel = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(loginPanel, "card3");

        informacaoPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout informacaoPanelLayout = new javax.swing.GroupLayout(informacaoPanel);
        informacaoPanel.setLayout(informacaoPanelLayout);
        informacaoPanelLayout.setHorizontalGroup(
            informacaoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        informacaoPanelLayout.setVerticalGroup(
            informacaoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(informacaoPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel informacaoPanel;
    private javax.swing.JPanel loginPanel;
    // End of variables declaration//GEN-END:variables
}
