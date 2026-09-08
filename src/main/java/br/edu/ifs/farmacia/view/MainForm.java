package br.edu.ifs.farmacia.view;

import br.edu.ifs.farmacia.controller.MainController;
import br.edu.ifs.farmacia.view.component.PanelProdutos;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.popup.GlassPanePopup;
import raven.toast.Notifications;

public class MainForm extends javax.swing.JFrame {

    PanelProdutos panelProdutos;
    MainController mainController;

    public MainForm() {
        setTitle("PharmaStation - Gestão de Farmácia");
        carregarIcone();
        initComponents();
        init();
    }

    private void carregarIcone() {
        try {
            java.net.URL logoUrl = getClass().getResource("/imagens/logo.png");
            if (logoUrl != null) {
                setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(logoUrl));
            }
        } catch (Exception e) {
            // Log ou fallback silencioso
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
                JLabel label = new JLabel("Você realmente deseja sair?", JLabel.CENTER);
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
        panelProdutos = new PanelProdutos();
        this.setLayout(new GridLayout());
        this.add(panelProdutos);
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
