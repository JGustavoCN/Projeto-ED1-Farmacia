package br.edu.ifs.farmacia.view.component;

import br.edu.ifs.farmacia.view.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;

    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
        
    }

    private void init() {
        title = new JLabel("Login");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        description = new JLabel("Para conectar-se");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        description1 = new JLabel("Entre com seus dados pessoais");
        description1.setForeground(new Color(245, 245, 245));
        add(description1);
        button = new ButtonOutLine();
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(255, 255, 255));
        button.setText("ENTRAR");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);
            }
        });
        add(button, "w 60%, h 40");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(11, 148, 158), 0, getHeight(), new Color(6, 90, 96));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void informacaoLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void informacaoRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Informação");
                description.setText("Informação sobre o projeto");
                description1.setText("Um pouco sobre como foi feito o projeto");
                button.setText("SAIBA MAIS");
            } else {
                title.setText("Login");
                description.setText("Para conectar-se");
                description1.setText("Entre com seus dados pessoais");
                button.setText("LOGIN");
            }
            this.isLogin = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
