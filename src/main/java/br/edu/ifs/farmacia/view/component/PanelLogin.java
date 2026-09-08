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

        // Configuração do layout para o painel
        informacaoPanel.setLayout(new MigLayout("wrap", "[grow, center]", "push[]10[]10[]push")); // Ajusta o layout para centralizar e adicionar mais espaço vertical

        // Título
        JLabel tituloLabel = new JLabel("Informação");
        tituloLabel.setFont(new Font("sansserif", Font.BOLD, 30));
        tituloLabel.setForeground(new Color(7, 164, 121));
        informacaoPanel.add(tituloLabel, "cell 0 0, align center, gapbottom 20"); // Adiciona espaço abaixo do título

        // Informações
        JLabel informacoesLabel = new JLabel(
                "<html><div style='text-align: justify;'>"
                + "O projeto implementa um sistema de gerenciamento de estoque para uma farmácia, "
                + "desenvolvido em Java com interface gráfica construída usando Swing. Utilizando a arquitetura MVC, "
                + "o sistema permite a criação e gestão de usuários com diferentes níveis de acesso, "
                + "como administradores e funcionários. As principais funcionalidades incluem o controle de estoque, "
                + "registro de vendas, e geração de relatórios. A persistência de dados é feita por meio da serialização de objetos, "
                + "garantindo que as informações sejam mantidas entre sessões sem a necessidade de um banco de dados.</div></html>");

        informacoesLabel.setFont(new Font("sansserif", Font.PLAIN, 16));
        informacoesLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        informacoesLabel.setHorizontalAlignment(SwingConstants.LEFT); // Justificação é feita com HTML
        informacaoPanel.add(informacoesLabel, "cell 0 1, grow, align center, gapleft 30, gapright 30, gaptop 10, gapbottom 10"); // Adiciona espaçamento ao redor do texto

        // Ajustes finais para garantir que o painel ocupe o espaço necessário
        informacaoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona uma borda de 20 pixels ao redor do painel para garantir o espaço adicional
    }

    private void initLogin(ActionListener loginEvent) {
        loginPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        loginPanel.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(ImageLoader.loadImage("user.png"));
        txtUser.setHint("Nome");

        loginPanel.add(txtUser, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(ImageLoader.loadImage("pass.png"));
        txtPass.setHint("Senha");
        loginPanel.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Esqueceu sua senha ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(loginEvent);
        cmd.setText("Entrar");
        loginPanel.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = txtUser.getText().trim();
                password = String.valueOf(txtPass.getPassword()).trim();
            }
        });
        
        
        // Adicionar o ActionListener para os campos de texto
        ActionListener enterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Executar ação do botão quando Enter é pressionado
                cmd.doClick();
            }
        };

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
        return this.userName;
    }

    public String getPassword() {
        return this.password;
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
