package br.edu.ifs.farmacia.view;

import br.edu.ifs.farmacia.controller.MainController;
import br.edu.ifs.farmacia.util.ImageLoader;
import br.edu.ifs.farmacia.view.component.PanelProdutos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.popup.GlassPanePopup;
import raven.toast.Notifications;

import br.edu.ifs.farmacia.controller.LoginController;
import br.edu.ifs.farmacia.model.login.Usuario;
import br.edu.ifs.farmacia.view.component.PanelUsuarios;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

public class MainForm extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private PanelProdutos panelProdutos;
    private PanelUsuarios panelUsuarios;
    private transient MainController mainController;
    private transient LoginController loginController;

    public MainForm() {
        setTitle("PharmaStation - Gestão Farmacêutica");
        carregarIcone();
        initComponents();
        init();
    }

    private void carregarIcone() {
        try {
            java.util.List<java.awt.Image> icons = ImageLoader.loadWindowIcons("logo.png");
            if (!icons.isEmpty()) {
                setIconImages(icons);
            }
        } catch (Exception e) {
            // Fallback silencioso
        }
    }

    private static void modificarJFrame(JFrame frame) {

        GlassPanePopup.install(frame);
        Notifications.getInstance().setJFrame(frame);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
                    new SimpleModalBorder.Option("Sim", SimpleModalBorder.OK_OPTION),
                    new SimpleModalBorder.Option("Não", SimpleModalBorder.CANCEL_OPTION)
                };
                JLabel label = new JLabel("Você realmente deseja sair do PharmaStation?", JLabel.CENTER);
                label.setBorder(new EmptyBorder(15, 15, 15, 15));
                ModalDialog.showModal(frame, new SimpleModalBorder(label, "Sair", options, (mc, i) -> {
                    if (i == SimpleModalBorder.OK_OPTION) {
                        MainController.getInstance().salvarDados();
                        frame.dispose();
                        System.exit(0);
                    }
                }));

            }
        });
    }

    private void init() {
        mainController = MainController.getInstance();
        loginController = LoginController.getInstance();
        Usuario usuarioLogado = loginController.getUsuarioLogado();

        JPanel mainContainer = new JPanel(new BorderLayout(0, 0));

        // Cabeçalho institucional superior
        JPanel headerPanel = new JPanel(new MigLayout("fillx, insets 8 20 8 20", "[][grow][right]", "[]"));
        headerPanel.putClientProperty(FlatClientProperties.STYLE, "background:$Panel.background; border:0,0,1,0,$Component.borderColor");

        JLabel lblLogo = new JLabel(ImageLoader.loadImage("logo.png", 26, 26));
        JLabel lblBrand = new JLabel("PharmaStation");
        lblBrand.setFont(new Font("sansserif", Font.BOLD, 16));
        lblBrand.setForeground(new Color(11, 148, 158));

        headerPanel.add(lblLogo, "cell 0 0, gapright 8");
        headerPanel.add(lblBrand, "cell 0 0");

        // Informações da sessão
        String nome = (usuarioLogado != null) ? usuarioLogado.getUsername() : "Usuário";
        String perfil = (usuarioLogado != null && usuarioLogado.getTipo() != null) ? usuarioLogado.getTipo().getNome() : "Padrão";
        JLabel lblUserInfo = new JLabel("Conectado como: " + nome + " [" + perfil + "]");
        lblUserInfo.setFont(new Font("sansserif", Font.PLAIN, 12));
        lblUserInfo.setForeground(new Color(195, 195, 195));
        headerPanel.add(lblUserInfo, "cell 2 0, gapright 15");

        // Botão de Logout
        JButton btnLogout = new JButton("Encerrar Sessão");
        btnLogout.putClientProperty(FlatClientProperties.STYLE, "arc:10; margin:4,12,4,12; font:bold 11;");
        btnLogout.addActionListener((ActionEvent e) -> confirmarLogout());
        headerPanel.add(btnLogout, "cell 2 0");

        mainContainer.add(headerPanel, BorderLayout.NORTH);

        // Abas modernas de navegação
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.putClientProperty(FlatClientProperties.STYLE, ""
                + "tabType:underlined;"
                + "tabHeight:38;"
                + "font:bold 13;"
                + "tabInsets:0,25,0,25;"
                + "tabAreaInsets:0,10,0,10;");

        // Aba 1: Estoque de Medicamentos (acessível a todos)
        panelProdutos = new PanelProdutos();
        tabbedPane.addTab("Estoque de Medicamentos", panelProdutos);

        // Aba 2: Gestão de Usuários (ACESSÍVEL APENAS PARA ADMINISTRADORES)
        if (loginController.isAdministrador()) {
            panelUsuarios = new PanelUsuarios();
            tabbedPane.addTab("Gestão de Usuários", panelUsuarios);
            tabbedPane.addChangeListener(e -> {
                if (tabbedPane.getSelectedComponent() == panelUsuarios) {
                    panelUsuarios.carregarDados();
                }
            });
        }

        mainContainer.add(tabbedPane, BorderLayout.CENTER);

        this.setContentPane(mainContainer);
    }

    private void confirmarLogout() {
        SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
            new SimpleModalBorder.Option("Não", SimpleModalBorder.CANCEL_OPTION),
            new SimpleModalBorder.Option("Sim, Sair", SimpleModalBorder.OK_OPTION)
        };
        JLabel label = new JLabel("Deseja encerrar a sessão atual e retornar à tela de login?", JLabel.CENTER);
        label.setBorder(new EmptyBorder(15, 20, 15, 20));
        ModalDialog.showModal(this, new SimpleModalBorder(label, "Encerrar Sessão", options, (mc, i) -> {
            if (i == SimpleModalBorder.OK_OPTION) {
                mc.close();
                mainController.deslogar();
            }
        }));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1291, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void start() {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            modificarJFrame(mainForm);
            MainController.getInstance().setTelaAtual(mainForm);
            mainForm.setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
