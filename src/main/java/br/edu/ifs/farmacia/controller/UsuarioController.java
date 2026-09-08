package br.edu.ifs.farmacia.controller;

import br.edu.ifs.farmacia.model.login.Administrador;
import br.edu.ifs.farmacia.model.login.Funcionario;
import br.edu.ifs.farmacia.model.login.TipoUsuario;
import br.edu.ifs.farmacia.model.login.Usuario;
import br.edu.ifs.farmacia.repository.UsuarioRepository;
import br.edu.ifs.farmacia.util.Lista;

/**
 * Controller responsável pelo gerenciamento de Usuários (CRUD e RBAC).
 * Conforme regras do sistema, todas as operações administrativas são
 * restritas a usuários com perfil Administrador.
 *
 * @author PharmaStation
 */
public class UsuarioController {

    private static UsuarioController instance;
    private final UsuarioRepository usuarioRepository;

    private UsuarioController() {
        this.usuarioRepository = UsuarioRepository.getInstance();
    }

    public static synchronized UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    /**
     * Valida se o usuário atualmente autenticado possui perfil de Administrador.
     */
    public boolean isOperacaoPermitida() {
        return LoginController.getInstance().isAdministrador();
    }

    /**
     * Retorna a lista de todos os usuários cadastrados.
     * Restrito a Administradores.
     */
    public Lista<Usuario> listarTodos() {
        if (!isOperacaoPermitida()) {
            throw new SecurityException("Acesso negado: apenas administradores podem visualizar os usuários.");
        }
        return usuarioRepository.buscarTodos();
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param username Nome de login (mínimo 3 caracteres, sem espaços)
     * @param password Senha de acesso (mínimo 4 caracteres)
     * @param tipo TipoUsuario (ADMINISTRADOR ou FUNCIONARIO)
     * @return true se cadastrado com sucesso, false caso contrário
     */
    public boolean cadastrar(String username, String password, TipoUsuario tipo) {
        if (!isOperacaoPermitida()) {
            throw new SecurityException("Acesso negado: apenas administradores podem cadastrar novos usuários.");
        }
        validarDadosUsuario(username, password, tipo);

        Usuario novoUsuario = criarInstanciaUsuario(username.trim(), password.trim(), tipo);
        return usuarioRepository.adicionar(novoUsuario);
    }

    /**
     * Atualiza os dados de um usuário existente.
     */
    public boolean atualizar(String usernameAntigo, String novoUsername, String novaSenha, TipoUsuario novoTipo) {
        if (!isOperacaoPermitida()) {
            throw new SecurityException("Acesso negado: apenas administradores podem editar usuários.");
        }
        validarDadosUsuario(novoUsername, novaSenha, novoTipo);

        Usuario usuarioAtualizado = criarInstanciaUsuario(novoUsername.trim(), novaSenha.trim(), novoTipo);
        return usuarioRepository.atualizar(usernameAntigo, usuarioAtualizado);
    }

    /**
     * Remove um usuário do sistema, impedindo auto-exclusão ou exclusão do último administrador.
     */
    public boolean remover(Usuario usuario) {
        if (!isOperacaoPermitida()) {
            throw new SecurityException("Acesso negado: apenas administradores podem remover usuários.");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido para remoção.");
        }

        Usuario logado = LoginController.getInstance().getUsuarioLogado();
        if (logado != null && logado.getUsername().equalsIgnoreCase(usuario.getUsername())) {
            throw new IllegalStateException("Você não pode excluir o usuário com o qual está atualmente conectado.");
        }

        // Garante que o sistema sempre mantenha pelo menos 1 administrador
        if (usuario.getTipo() == TipoUsuario.ADMINISTRADOR) {
            int totalAdmins = contarAdministradores();
            if (totalAdmins <= 1) {
                throw new IllegalStateException("Não é possível remover o único administrador cadastrado no sistema.");
            }
        }

        return usuarioRepository.remover(usuario);
    }

    /**
     * Busca usuário por username.
     */
    public Usuario buscarPorUsername(String username) {
        if (!isOperacaoPermitida()) {
            throw new SecurityException("Acesso negado.");
        }
        return usuarioRepository.buscarPorUsername(username);
    }

    /**
     * Conta a quantidade de administradores ativos no repositório.
     */
    public int contarAdministradores() {
        Lista<Usuario> todos = usuarioRepository.buscarTodos();
        int count = 0;
        for (int i = 0; i < todos.tamanho(); i++) {
            if (todos.pegar(i).getTipo() == TipoUsuario.ADMINISTRADOR) {
                count++;
            }
        }
        return count;
    }

    private void validarDadosUsuario(String username, String password, TipoUsuario tipo) {
        if (username == null || username.trim().length() < 3) {
            throw new IllegalArgumentException("O nome de usuário deve ter pelo menos 3 caracteres.");
        }
        if (username.trim().contains(" ")) {
            throw new IllegalArgumentException("O nome de usuário não pode conter espaços.");
        }
        if (password == null || password.trim().length() < 4) {
            throw new IllegalArgumentException("A senha deve conter no mínimo 4 caracteres.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Selecione um tipo de perfil válido.");
        }
    }

    private Usuario criarInstanciaUsuario(String username, String password, TipoUsuario tipo) {
        if (tipo == TipoUsuario.ADMINISTRADOR) {
            return new Administrador(username, password);
        } else {
            return new Funcionario(username, password);
        }
    }
}
