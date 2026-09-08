package br.edu.ifs.farmacia.view.component;

import br.edu.ifs.farmacia.controller.LoginController;
import br.edu.ifs.farmacia.controller.UsuarioController;
import br.edu.ifs.farmacia.model.login.Usuario;
import br.edu.ifs.farmacia.util.Lista;
import br.edu.ifs.farmacia.view.swing.table.ButtonAction;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.toast.Notifications;

/**
 * Painel de Gestão de Usuários e Controle de Acesso (RBAC).
 * Exclusivo para usuários com perfil de Administrador.
 *
 * @author PharmaStation
 */
public class PanelUsuarios extends JPanel {

    private static final long serialVersionUID = 1L;

    private final transient UsuarioController usuarioController;
    private final transient LoginController loginController;

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private ButtonAction cmdNew;
    private ButtonAction cmdEdit;
    private ButtonAction cmdDelete;
    private ButtonAction cmdRefresh;

    public PanelUsuarios() {
        this.usuarioController = UsuarioController.getInstance();
        this.loginController = LoginController.getInstance();
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Top Panel: Título e Ações
        JPanel topPanel = new JPanel(new MigLayout("fillx, insets 0", "[grow][right]", "[]12[]"));

        JLabel lbTitle = new JLabel("PharmaStation • Gestão de Usuários");
        lbTitle.setFont(new Font("sansserif", Font.BOLD, 22));
        lbTitle.setForeground(new Color(11, 148, 158));
        topPanel.add(lbTitle, "cell 0 0");

        // Informação do perfil atual no topo
        Usuario logado = loginController.getUsuarioLogado();
        String nomeLogado = logado != null ? logado.getUsername() : "Admin";
        JLabel lbSession = new JLabel("Sessão Administrativa: " + nomeLogado);
        lbSession.setFont(new Font("sansserif", Font.ITALIC, 12));
        lbSession.setForeground(new Color(195, 195, 195));
        topPanel.add(lbSession, "cell 1 0, align right");

        // Barra de Ferramentas: Busca + Botões CRUD
        JPanel toolbar = new JPanel(new MigLayout("insets 0", "[grow, 250:350:450][10][]5[]5[]5[]", "[]"));

        txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pesquisar por usuário ou tipo...");
        txtSearch.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,10,5,10;");
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarDados();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarDados();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarDados();
            }
        });
        toolbar.add(txtSearch, "cell 0 0, growx");

        cmdNew = new ButtonAction();
        cmdNew.setText("Novo Usuário");
        cmdNew.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "margin:6,16,6,16;"
                + "background:#0B949E;"
                + "foreground:#FFFFFF;"
                + "font:bold 12;");
        cmdNew.addActionListener((ActionEvent e) -> abrirModalCriar());
        toolbar.add(cmdNew, "cell 2 0");

        cmdEdit = new ButtonAction();
        cmdEdit.setText("Editar");
        cmdEdit.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:6,16,6,16; font:bold 12;");
        cmdEdit.addActionListener((ActionEvent e) -> abrirModalEditar());
        toolbar.add(cmdEdit, "cell 3 0");

        cmdDelete = new ButtonAction();
        cmdDelete.setText("Excluir");
        cmdDelete.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "margin:6,16,6,16;"
                + "background:#dc3545;"
                + "foreground:#FFFFFF;"
                + "font:bold 12;");
        cmdDelete.addActionListener((ActionEvent e) -> confirmarExclusao());
        toolbar.add(cmdDelete, "cell 4 0");

        cmdRefresh = new ButtonAction();
        cmdRefresh.setText("Atualizar");
        cmdRefresh.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:6,16,6,16;");
        cmdRefresh.addActionListener((ActionEvent e) -> carregarDados());
        toolbar.add(cmdRefresh, "cell 5 0");

        topPanel.add(toolbar, "cell 0 1 2 1, growx");

        add(topPanel, BorderLayout.NORTH);

        // Tabela de Usuários
        model = new DefaultTableModel(new Object[]{"#", "Nome de Usuário", "Nível de Acesso", "Sessão Atual", "Objeto"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(36);
        table.getTableHeader().setReorderingAllowed(false);
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;");

        // Ocultar coluna de objeto
        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);

        // Larguras das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(180);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);

        // Renderizadores
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // Renderizador customizado para Sessão Atual com destaque em verde
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                if (value != null && value.toString().contains("Conectado")) {
                    setFont(getFont().deriveFont(Font.BOLD));
                    setForeground(new Color(40, 167, 69));
                } else {
                    setFont(getFont().deriveFont(Font.PLAIN));
                    setForeground(new Color(150, 150, 150));
                }
                return c;
            }
        });

        // Renderizador estilizado para o tipo de usuário
        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                setFont(getFont().deriveFont(Font.BOLD));
                if (value != null && value.toString().contains("Administrador")) {
                    setForeground(new Color(11, 148, 158));
                } else {
                    setForeground(new Color(100, 149, 237));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "border:null;");
        add(scrollPane, BorderLayout.CENTER);
    }

    public void carregarDados() {
        filtrarDados();
    }

    private void filtrarDados() {
        if (!loginController.isAdministrador()) {
            model.setRowCount(0);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Acesso restrito ao perfil Administrador.");
            return;
        }

        String termo = txtSearch != null ? txtSearch.getText().trim().toLowerCase() : "";
        model.setRowCount(0);

        try {
            Lista<Usuario> usuarios = usuarioController.listarTodos();
            Usuario logado = loginController.getUsuarioLogado();
            int contador = 1;

            for (int i = 0; i < usuarios.tamanho(); i++) {
                Usuario u = usuarios.pegar(i);
                boolean matches = termo.isEmpty()
                        || u.getUsername().toLowerCase().contains(termo)
                        || u.getTipo().getNome().toLowerCase().contains(termo);

                if (matches) {
                    boolean isEu = logado != null && logado.getUsername().equalsIgnoreCase(u.getUsername());
                    String statusSessao = isEu ? "Conectado" : "-";
                    model.addRow(new Object[]{
                        contador++,
                        u.getUsername(),
                        u.getTipo().getNome(),
                        statusSessao,
                        u
                    });
                }
            }
        } catch (SecurityException ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, ex.getMessage());
        }
    }

    private Usuario getUsuarioSelecionado() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            return (Usuario) table.getValueAt(row, 4);
        }
        return null;
    }

    private void abrirModalCriar() {
        PanelCreateUsuario panelCreate = new PanelCreateUsuario();

        SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
            new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
            new SimpleModalBorder.Option("Cadastrar", SimpleModalBorder.OK_OPTION)
        };

        ModalDialog.showModal(this, new SimpleModalBorder(panelCreate, "Novo Usuário", options, (mc, i) -> {
            if (i == SimpleModalBorder.OK_OPTION) {
                if (!panelCreate.validar()) {
                    return;
                }
                try {
                    boolean ok = usuarioController.cadastrar(
                            panelCreate.getUsername(),
                            panelCreate.getPassword(),
                            panelCreate.getTipoSelecionado()
                    );
                    if (ok) {
                        mc.close();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Usuário cadastrado com sucesso!");
                        carregarDados();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Nome de usuário já existe no sistema.");
                    }
                } catch (Exception ex) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, ex.getMessage());
                }
            }
        }));
    }

    private void abrirModalEditar() {
        Usuario selecionado = getUsuarioSelecionado();
        if (selecionado == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Selecione um usuário na tabela para editar.");
            return;
        }

        PanelCreateUsuario panelEdit = new PanelCreateUsuario();
        panelEdit.loadData(selecionado);

        SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
            new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
            new SimpleModalBorder.Option("Salvar Alterações", SimpleModalBorder.OK_OPTION)
        };

        ModalDialog.showModal(this, new SimpleModalBorder(panelEdit, "Editar Usuário [" + selecionado.getUsername() + "]", options, (mc, i) -> {
            if (i == SimpleModalBorder.OK_OPTION) {
                if (!panelEdit.validar()) {
                    return;
                }
                try {
                    boolean ok = usuarioController.atualizar(
                            panelEdit.getUsernameOriginal(),
                            panelEdit.getUsername(),
                            panelEdit.getPassword(),
                            panelEdit.getTipoSelecionado()
                    );
                    if (ok) {
                        mc.close();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Usuário atualizado com sucesso!");
                        carregarDados();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Não foi possível atualizar: nome de usuário em uso.");
                    }
                } catch (Exception ex) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, ex.getMessage());
                }
            }
        }));
    }

    private void confirmarExclusao() {
        Usuario selecionado = getUsuarioSelecionado();
        if (selecionado == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Selecione um usuário na tabela para excluir.");
            return;
        }

        SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
            new SimpleModalBorder.Option("Não", SimpleModalBorder.CANCEL_OPTION),
            new SimpleModalBorder.Option("Sim, Excluir", SimpleModalBorder.OK_OPTION)
        };

        JLabel lblAviso = new JLabel("<html>Deseja realmente remover o usuário <b>" + selecionado.getUsername() + "</b>?<br/>Esta ação é irreversível.</html>", JLabel.CENTER);
        lblAviso.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        ModalDialog.showModal(this, new SimpleModalBorder(lblAviso, "Confirmar Exclusão", options, (mc, i) -> {
            if (i == SimpleModalBorder.OK_OPTION) {
                try {
                    boolean removido = usuarioController.remover(selecionado);
                    if (removido) {
                        mc.close();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Usuário removido com sucesso!");
                        carregarDados();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Usuário não foi encontrado para remoção.");
                    }
                } catch (Exception ex) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, ex.getMessage());
                }
            }
        }));
    }
}
