package br.edu.ifs.farmacia.controller;

import br.edu.ifs.farmacia.repository.UsuarioRepository;
import br.edu.ifs.farmacia.view.MainForm;
import javax.swing.JFrame;

/**
 *
 * @author Aluno
 */
public class MainController {
    
    private JFrame telaAtual;
    private final ProdutoController produtoController = ProdutoController.getInstance();
    private final VendaController vendaController = VendaController.getInstance();
    
    public void logar(boolean respostaLoginController){
        if (respostaLoginController == false) return;
        telaAtual.dispose();
        MainForm.start();
    }
    
    public JFrame getTelaAtual() {
        return telaAtual;
    }

    public void setTelaAtual(JFrame telaAtual) {
        this.telaAtual = telaAtual;
    }
    
    public void salvarDados(){
        UsuarioRepository.getInstance().salvarTodos();
        produtoController.salvarDados();
        vendaController.salvarDados();
    }
    
    private MainController() {
    }
    
    public static MainController getInstance() {
        return MainControllerHolder.INSTANCE;
    }
    
    private static class MainControllerHolder {

        private static final MainController INSTANCE = new MainController();
    }
}
