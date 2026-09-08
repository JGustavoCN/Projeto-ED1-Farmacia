package br.edu.ifs.farmacia.view.component;

import br.edu.ifs.farmacia.model.login.TipoUsuario;
import br.edu.ifs.farmacia.model.login.Usuario;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 * Painel moderno de criação e edição de Usuário.
 * Utilizado dentro dos modais de administração.
 *
 * @author PharmaStation
 */
public class PanelCreateUsuario extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JComboBox<TipoUsuario> comboTipo;
    private JLabel lblStatus;
    private boolean isEdicao = false;
    private String usernameOriginal;

    public PanelCreateUsuario() {
        initUI();
    }

    private void initUI() {
        setLayout(new MigLayout("wrap 2, insets 20 25 20 25, gap 12", "[right, 120!][grow, 260!]"));

        // Informação descritiva no topo
        JLabel lblHeader = new JLabel("Credenciais e Nível de Acesso");
        lblHeader.setFont(new Font("sansserif", Font.BOLD, 14));
        lblHeader.setForeground(new Color(11, 148, 158));
        add(lblHeader, "span 2, growx, gapbottom 10");

        // Campo Usuário
        JLabel lblUser = new JLabel("Nome de Usuário:");
        lblUser.setFont(new Font("sansserif", Font.PLAIN, 13));
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex: joao.silva");
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        add(txtUsername, "growx");

        // Campo Perfil / Tipo de Usuário
        JLabel lblTipo = new JLabel("Nível de Acesso:");
        lblTipo.setFont(new Font("sansserif", Font.PLAIN, 13));
        add(lblTipo);

        comboTipo = new JComboBox<>(TipoUsuario.values());
        comboTipo.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        add(comboTipo, "growx");

        // Campo Senha
        JLabel lblPass = new JLabel("Senha de Acesso:");
        lblPass.setFont(new Font("sansserif", Font.PLAIN, 13));
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mínimo 4 caracteres");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "arc:10; showRevealButton:true");
        add(txtPassword, "growx");

        // Campo Confirmar Senha
        JLabel lblConfirm = new JLabel("Confirmar Senha:");
        lblConfirm.setFont(new Font("sansserif", Font.PLAIN, 13));
        add(lblConfirm);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Repita a senha");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "arc:10; showRevealButton:true");
        add(txtConfirmPassword, "growx");

        // Label para erros de validação visual
        lblStatus = new JLabel(" ");
        lblStatus.setForeground(new Color(220, 53, 69));
        lblStatus.setFont(new Font("sansserif", Font.ITALIC, 11));
        add(lblStatus, "span 2, align center, gaptop 5");
    }

    public void loadData(Usuario usuario) {
        if (usuario != null) {
            this.isEdicao = true;
            this.usernameOriginal = usuario.getUsername();
            txtUsername.setText(usuario.getUsername());
            comboTipo.setSelectedItem(usuario.getTipo());
            txtPassword.setText(usuario.getPassword());
            txtConfirmPassword.setText(usuario.getPassword());
        }
    }

    public boolean validar() {
        lblStatus.setText(" ");
        String user = getUsername();
        String pass = getPassword();
        String confirm = getConfirmPassword();

        if (user.isEmpty()) {
            lblStatus.setText("O nome de usuário não pode ficar em branco.");
            txtUsername.requestFocus();
            return false;
        }
        if (user.contains(" ")) {
            lblStatus.setText("O nome de usuário não pode conter espaços.");
            txtUsername.requestFocus();
            return false;
        }
        if (user.length() < 3) {
            lblStatus.setText("O usuário deve conter ao menos 3 caracteres.");
            txtUsername.requestFocus();
            return false;
        }
        if (pass.length() < 4) {
            lblStatus.setText("A senha deve conter ao menos 4 caracteres.");
            txtPassword.requestFocus();
            return false;
        }
        if (!pass.equals(confirm)) {
            lblStatus.setText("As senhas informadas não coincidem.");
            txtConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword()).trim();
    }

    public String getConfirmPassword() {
        return new String(txtConfirmPassword.getPassword()).trim();
    }

    public TipoUsuario getTipoSelecionado() {
        return (TipoUsuario) comboTipo.getSelectedItem();
    }

    public boolean isEdicao() {
        return isEdicao;
    }

    public String getUsernameOriginal() {
        return usernameOriginal;
    }
}
