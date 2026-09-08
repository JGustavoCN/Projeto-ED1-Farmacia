package br.edu.ifs.farmacia.controller;

import br.edu.ifs.farmacia.model.login.TipoUsuario;
import br.edu.ifs.farmacia.model.login.Usuario;
import br.edu.ifs.farmacia.repository.UsuarioRepository;

/**
 *
 * @author Aluno
 */
public class LoginController {
    
    private static LoginController instance;
    private final UsuarioRepository usuarioRepository;

    private Usuario usuarioLogado;
    
    private LoginController() {
        usuarioRepository = UsuarioRepository.getInstance();
    }

    public static LoginController getInstance() {
        
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }
    public boolean logar(final String userName, final String userPassword) {
        Usuario usuario = usuarioRepository.buscarPorNamePassword(userName, userPassword);
        if (usuario == null) return false;
        this.usuarioLogado = usuario;
        return true;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void logout() {
        this.usuarioLogado = null;
    }

    public boolean isAdministrador() {
        return loginAdministrador();
    }

    public boolean isFuncionario() {
        return loginFuncionario();
    }

    public boolean loginAdministrador() {
        return usuarioLogado != null && TipoUsuario.ADMINISTRADOR == usuarioLogado.getTipo();
    }

    public boolean loginFuncionario() {
        return usuarioLogado != null && TipoUsuario.FUNCIONARIO == usuarioLogado.getTipo();
    }
}
