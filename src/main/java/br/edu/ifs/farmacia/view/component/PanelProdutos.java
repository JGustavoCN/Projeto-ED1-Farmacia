package br.edu.ifs.farmacia.view.component;

import br.edu.ifs.farmacia.controller.ProdutoController;
import br.edu.ifs.farmacia.controller.VendaController;
import br.edu.ifs.farmacia.model.Produto;
import br.edu.ifs.farmacia.model.Venda;
import br.edu.ifs.farmacia.report.FieldReportEstoque;
import br.edu.ifs.farmacia.report.FieldReportVendas;
import br.edu.ifs.farmacia.report.ParameterReportEstoque;
import br.edu.ifs.farmacia.report.ParameterReportVendas;
import br.edu.ifs.farmacia.report.ReportManager;
import br.edu.ifs.farmacia.util.Lista;
import br.edu.ifs.farmacia.util.ProdutoJaExisteException;
import br.edu.ifs.farmacia.util.ProdutoNaoEncontradoException;
import br.edu.ifs.farmacia.util.ProdutoNaoPodeSerVendidoException;
import br.edu.ifs.farmacia.util.ProdutoUtil;
import br.edu.ifs.farmacia.util.VendaJaExisteException;
import br.edu.ifs.farmacia.util.VendaUtil;
import br.edu.ifs.farmacia.view.swing.table.CheckBoxTableHeaderRenderer;
import br.edu.ifs.farmacia.view.swing.table.TableHeaderAlignment;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import br.edu.ifs.farmacia.util.ImageLoader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author Aluno
 */
public class PanelProdutos extends javax.swing.JPanel {

    private final ProdutoController produtoController;
    private final VendaController vendaController;

    public PanelProdutos() {
        produtoController = ProdutoController.getInstance();
        this.vendaController = VendaController.getInstance();
        initComponents();
        init();

    }

    private void init() {

        switchDarkLight.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        switchDarkLight.addActionListener(new ActionListener() {

            private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
            private ScheduledFuture<?> scheduledFuture;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                scheduledFuture = scheduled.schedule(() -> {
                    changeThemes(switchDarkLight.isSelected());
                }, 500, TimeUnit.MILLISECONDS);
            }
        });
        
        try {
            ReportManager.getInstance().compileReport();
        } catch (JRException e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.WARNING, "Aviso ao carregar modelos de relatórios: " + e.getMessage());
        }

        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");

        // Cabecalho institucional com Logo oficial e titulo destacado
        lbTitle.setIcon(ImageLoader.loadImage("logo.png", 32, 32));
        lbTitle.setText(" PharmaStation • Controle de Estoque");
        lbTitle.setIconTextGap(10);
        lbTitle.setForeground(new Color(11, 148, 158));
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar por nome, código ou marca...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("imagens/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");

        // Busca reativa em tempo real na tabela ao digitar
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                loadData();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                loadData();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                loadData();
            }
        });

        // Estilizacao dos botoes de acao com identidade visual e pesos semanticos
        cmdNew.setText("Novo");
        cmdNew.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "background:#0B949E;"
                + "foreground:#FFFFFF;"
                + "font:bold;"
                + "margin:5,15,5,15;");

        cmdVender.setText("Vender");
        cmdVender.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "background:#107C41;"
                + "foreground:#FFFFFF;"
                + "font:bold;"
                + "margin:5,15,5,15;");

        cmdDelete.setText("Excluir");
        cmdDelete.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "hoverBackground:#D9534F25;"
                + "margin:5,15,5,15;");
        cmdDelete.addActionListener((ActionEvent evt) -> {
            Lista<Produto> selecionados = getSelectedData();
            if (selecionados.estaVazia()) {
                Toast.show(this, Toast.Type.WARNING, "Por favor selecione pelo menos um produto para excluir");
                return;
            }
            String mensagem = selecionados.tamanho() == 1
                    ? "Deseja realmente excluir o produto '" + selecionados.pegar(0).getNome() + "'?"
                    : "Deseja realmente excluir os " + selecionados.tamanho() + " produtos selecionados?";

            SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
                new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
                new SimpleModalBorder.Option("Excluir", SimpleModalBorder.OK_OPTION)
            };

            javax.swing.JLabel lblMsg = new javax.swing.JLabel(mensagem);
            lblMsg.setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10));
            ModalDialog.showModal(this, new SimpleModalBorder(lblMsg, "Confirmar Exclusão", options, (mc, i) -> {
                if (i == SimpleModalBorder.OK_OPTION) {
                    int removidos = 0;
                    for (int j = 0; j < selecionados.tamanho(); j++) {
                        try {
                            produtoController.remover(selecionados.pegar(j));
                            removidos++;
                        } catch (ProdutoNaoEncontradoException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                    Toast.show(this, Toast.Type.SUCCESS, removidos + " produto(s) excluído(s) com sucesso");
                    loadData();
                }
            }));
        });

        cmdEdit.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,15,5,15;");
        cmdRelatorioEstoque.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,15,5,15;");
        cmdRelatorioVendas.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,15,5,15;");

        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

        // Renderizador com alerta visual para produtos com Estoque Baixo (<= 5 unidades)
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                if (value instanceof Number) {
                    int qtd = ((Number) value).intValue();
                    if (qtd <= 5) {
                        setFont(getFont().deriveFont(Font.BOLD));
                        if (!isSelected) {
                            setForeground(new Color(217, 83, 79));
                            setToolTipText("Atenção: Estoque Baixo (" + qtd + " unidade(s))! Necessária reposição.");
                        }
                    } else {
                        if (!isSelected) {
                            setForeground(jtable.getForeground());
                            setToolTipText(null);
                        }
                    }
                }
                return this;
            }
        });

        loadData();
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        model.setRowCount(0);

        Lista<Produto> lista = checkOrdenarPorNome.isSelected() ? produtoController.listaOrdenadaPorNome() : produtoController.lista();
        String termo = (txtSearch != null && txtSearch.getText() != null) ? txtSearch.getText().trim().toLowerCase() : "";

        int contador = 1;
        for (int i = 0; i < lista.tamanho(); i++) {
            Produto p = lista.pegar(i);
            if (termo.isEmpty() || correspondeBusca(p, termo)) {
                model.addRow(ProdutoUtil.produtoToTableRow(p, contador++));
            }
        }
    }

    private boolean correspondeBusca(Produto p, String termo) {
        if (p == null) {
            return false;
        }
        if (p.getNome() != null && p.getNome().toLowerCase().contains(termo)) {
            return true;
        }
        if (p.getMarca() != null && p.getMarca().toLowerCase().contains(termo)) {
            return true;
        }
        if (String.valueOf(p.getCodigo()).contains(termo)) {
            return true;
        }
        if (p.getDescricao() != null && p.getDescricao().toLowerCase().contains(termo)) {
            return true;
        }
        return false;
    }

    private Lista<Produto> getSelectedData() {
        final Lista<Produto> list = new Lista<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 0)) {
                Produto data = (Produto) table.getValueAt(i, 3);
                list.adicionar(data);
            }
        }
        return list;
    }

    private void changeThemes(boolean dark) {
        if (FlatLaf.isLafDark() != dark) {
            if (!dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        darkLightSwitchIcon = new br.edu.ifs.farmacia.view.swing.DarkLightSwitchIcon();
        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdEdit = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        cmdDelete = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        cmdNew = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        checkOrdenarPorNome = new javax.swing.JCheckBox();
        cmdVender = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        lblRelatorios = new javax.swing.JLabel();
        cmdRelatorioEstoque = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        cmdRelatorioVendas = new br.edu.ifs.farmacia.view.swing.table.ButtonAction();
        switchDarkLight = new javax.swing.JToggleButton();

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SELECT", "#", "CODIGO", "NOME", "MARCA", "DESCRICAO", "QUANTIDADE", "VALOR ENTRADA", "VALOR SAIDA"
            }
        ) {
            Class<?>[] types = new Class<?> [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMinWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        lbTitle.setText("PRODUTO");

        cmdEdit.setText("Editar");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdDelete.setText("Deletar");

        cmdNew.setText("Novo");
        cmdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewActionPerformed(evt);
            }
        });

        checkOrdenarPorNome.setText("Ordenar por Nome");
        checkOrdenarPorNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOrdenarPorNomeActionPerformed(evt);
            }
        });

        cmdVender.setText("Vender");
        cmdVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVenderActionPerformed(evt);
            }
        });

        lblRelatorios.setText("Relatórios:");

        cmdRelatorioEstoque.setText("Estoque");
        cmdRelatorioEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRelatorioEstoqueActionPerformed(evt);
            }
        });

        cmdRelatorioVendas.setText("Vendas");
        cmdRelatorioVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRelatorioVendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addComponent(jSeparator1)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(checkOrdenarPorNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdNew, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdVender, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdRelatorioEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRelatorioVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOrdenarPorNome)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdVender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRelatorioVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRelatorios)
                    .addComponent(cmdRelatorioEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        switchDarkLight.setIcon(darkLightSwitchIcon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(switchDarkLight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(switchDarkLight, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        PanelCreateProduto create = new PanelCreateProduto();
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancelar", "Confirmar"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Adicionar Produto", actions, (pc, i) -> {
            if (i == 1) {
                // save
                try {
                    produtoController.cadastrar(create.getData());
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Produto foi adicionado");
                    loadData();
                } catch (ProdutoJaExisteException e) {
                    Notifications.getInstance().show(Notifications.Type.INFO, "Produto já existe");
                    System.err.println(e.getMessage());
                }
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed

    private void checkOrdenarPorNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOrdenarPorNomeActionPerformed
        loadData();
    }//GEN-LAST:event_checkOrdenarPorNomeActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        Lista<Produto> list = getSelectedData();
        Produto produtoMaisBarato = ProdutoUtil.produtoMaisBarato(list);
        if (!list.estaVazia()) {
            if (list.tamanho() == 1) {
                Produto data = list.pegar(0);
                PanelCreateProduto create = new PanelCreateProduto();
                create.loadData(data);
                SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
                    new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
                    new SimpleModalBorder.Option("Atualizar", SimpleModalBorder.OK_OPTION)
                };
                ModalDialog.showModal(this, new SimpleModalBorder(create, "Editar Produto [" + data.getNome() + "]", options, (mc, i) -> {
                    if (i == SimpleModalBorder.OK_OPTION) {
                        try {
                            // editar
                            Produto dataEdit = create.getData();
                            if (!(dataEdit.getValorSaida() < 0 || dataEdit.getValorEntrada() < 0 || dataEdit.getQuantidadeEstoque() < 0)) {
                                dataEdit.setCodigo(data.getCodigo());
                                produtoController.atualizar(dataEdit);
                                Toast.show(this, Toast.Type.SUCCESS, "Produto foi alterado");
                            }

                            if (dataEdit.getValorSaida() < 0) {
                                Toast.show(this,
                                        Toast.Type.ERROR, "Alteração invalida: Produto " + produtoMaisBarato.getNome() + " não pode ser vendido por ->  " + dataEdit.getValorSaida());
                            }
                            if (dataEdit.getValorEntrada() < 0) {
                                Toast.show(this,
                                        Toast.Type.ERROR, "Alteração invalida: Produto " + produtoMaisBarato.getNome() + " não pode ter sido comprado por ->  " + dataEdit.getValorEntrada());
                            }
                            if (dataEdit.getQuantidadeEstoque() < 0) {
                                Toast.show(this,
                                        Toast.Type.ERROR, "Alteração invalida: Produto " + produtoMaisBarato.getNome() + ", quantidade não pode ser negativa ->  " + dataEdit.getQuantidadeEstoque());
                            }

                            loadData();
                        } catch (ProdutoNaoEncontradoException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }

                }));
            } else {
                PanelProductPriceEditor editar = new PanelProductPriceEditor(list);
                SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
                    new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
                    new SimpleModalBorder.Option("Atualizar", SimpleModalBorder.OK_OPTION)
                };
                ModalDialog.showModal(this, new SimpleModalBorder(editar, "Editar o valor de " + list.tamanho() + " produtos", options, (mc, i) -> {

                    if (produtoMaisBarato.getValorSaida() + editar.getValor() < 0) {
                        Toast.show(this,
                                Toast.Type.ERROR, "Alteração invalida: Produto " + produtoMaisBarato.getNome() + " vale " + produtoMaisBarato.getValorSaida());
                    } else if (i == SimpleModalBorder.OK_OPTION) {
                        try {
                            // editar Todos os valores 
                            produtoController.atualizarPrecoDeTodos(list, editar.getValor());
                            Toast.show(this, Toast.Type.SUCCESS, "Produtos foram alterados");
                            loadData();
                        } catch (ProdutoNaoEncontradoException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }

                }));
            }
        } else {
            Toast.show(this, Toast.Type.WARNING, "Por favor selecione pelo menos um produto");
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVenderActionPerformed
        Lista<Produto> list = getSelectedData();
        Lista<Venda> vendidos = new Lista<>();
        for (int i = 0; i < list.tamanho(); i++) {
            try {
                vendidos.adicionar(new Venda(list.pegar(i)));
            } catch (ProdutoNaoPodeSerVendidoException ex) {
                System.err.println(ex.getMessage());
                Toast.show(this, Toast.Type.ERROR, ex.getMessage());
            }
        }
        if (!vendidos.estaVazia()) {

            PanelVenda venda = new PanelVenda(vendidos);
            SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{
                new SimpleModalBorder.Option("Cancelar", SimpleModalBorder.CANCEL_OPTION),
                new SimpleModalBorder.Option("Vender", SimpleModalBorder.OK_OPTION)
            };
            ModalDialog.showModal(this, new SimpleModalBorder(venda, "Produtos vendidos [" + vendidos.tamanho() + "]", options, (mc, i) -> {
                if (i == SimpleModalBorder.OK_OPTION) {
                    try {
                        vendaController.cadastrarTodos(venda.getVendas());
                    } catch (VendaJaExisteException e) {
                        System.err.println(e.getMessage());
                    }

                    Toast.show(this, Toast.Type.SUCCESS, "Produtos foram vendidos");

                    loadData();
                }
            }));
        } else {
            Toast.show(this, Toast.Type.WARNING, "Por favor selecione pelo menos um produto válido para venda");
        }
    }//GEN-LAST:event_cmdVenderActionPerformed

    private void cmdRelatorioVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRelatorioVendasActionPerformed
        try {
            NumberFormat nf = new DecimalFormat("R$ #,##0.00");
            List<FieldReportVendas> fields = new ArrayList<>();
            Lista<Venda> vendas = vendaController.lista();
            for (int i = 0; i < vendas.tamanho(); i++) {
                Venda data = vendas.pegar(i);
                fields.add(new FieldReportVendas(
                        Integer.toString(i + 1),
                        Integer.toString(data.getProduto().getCodigo()),
                        data.getProduto().getNome(),
                        data.getProduto().getMarca(),
                        nf.format(data.getValorVendido()),
                        Integer.toString(data.getQuantidade()),
                        nf.format(data.getTotal())
                ));
            }
            double totalVendido = VendaUtil.totalVendido(vendas);
            int totalQuantidade = VendaUtil.totalQuantidade(vendas);
            
            ParameterReportVendas dataprint = new ParameterReportVendas(
                    nf.format(totalVendido),
                    Integer.toString(totalQuantidade),
                    fields
            );
            ReportManager.getInstance().printReportVendas(dataprint);
        } catch (JRException e) {
            e.printStackTrace();
            Toast.show(this, Toast.Type.WARNING, "Não foi possivel mostrar o relatório de vendas");
        }
    }//GEN-LAST:event_cmdRelatorioVendasActionPerformed

    private void cmdRelatorioEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRelatorioEstoqueActionPerformed
        try {
            NumberFormat nf = new DecimalFormat("R$ #,##0.00");
            List<FieldReportEstoque> fields = new ArrayList<>();
            Lista<Produto> produtos = produtoController.lista();
            for (int i = 0; i < produtos.tamanho(); i++) {
                Produto data = produtos.pegar(i);
                fields.add(new FieldReportEstoque(
                        Integer.toString(i + 1),
                        Integer.toString(data.getCodigo()),
                        data.getNome(),
                        data.getMarca(),
                        data.getDescricao(),
                        Integer.toString(data.getQuantidadeEstoque()),
                        nf.format(data.getValorEntrada()),
                        nf.format(data.getValorSaida())
                ));
            }
            double totalEntrada = ProdutoUtil.totalEntrada(produtos);
            double totalSaida = ProdutoUtil.totalSaida(produtos);
            int totalQuantidade = ProdutoUtil.totalQuantidade(produtos);
            double totalLucro = ProdutoUtil.totalLucro(produtos);
            ParameterReportEstoque dataprint = new ParameterReportEstoque(
                    nf.format(totalEntrada),
                    nf.format(totalSaida),
                    nf.format(totalLucro),
                    Integer.toString(totalQuantidade),
                    fields
            );
            ReportManager.getInstance().printReportEstoque(dataprint);
        } catch (JRException e) {
            e.printStackTrace();
            Toast.show(this, Toast.Type.WARNING, "Não foi possivel mostrar o relatório de estoque");
        }
    }//GEN-LAST:event_cmdRelatorioEstoqueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkOrdenarPorNome;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdDelete;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdEdit;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdNew;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdRelatorioEstoque;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdRelatorioVendas;
    private br.edu.ifs.farmacia.view.swing.table.ButtonAction cmdVender;
    private br.edu.ifs.farmacia.view.swing.DarkLightSwitchIcon darkLightSwitchIcon;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblRelatorios;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JToggleButton switchDarkLight;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
