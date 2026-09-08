package br.edu.ifs.farmacia.repository;

import br.edu.ifs.farmacia.model.login.Usuario;
import br.edu.ifs.farmacia.persistence.UsuarioDataManager;
import br.edu.ifs.farmacia.util.Lista;
import java.io.Serializable;

/**
 * Singleton para gerenciamento de usuários.
 */
public class UsuarioRepository implements Serializable {

    private static final long serialVersionUID = 1L;
    // Instância única do Singleton
    private static UsuarioRepository instance;

    // Lista de usuários
    private final Lista<Usuario> usuarios;

    // Construtor privado para evitar instância externa
    private UsuarioRepository() {
        this.usuarios = new Lista<>();
    }

    // Cria uma nova instância limpa sem passar pelo DataManager (evita recursão)
    public static UsuarioRepository createEmpty() {
        return new UsuarioRepository();
    }

    // Método para obter a instância única do Singleton
    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = UsuarioDataManager.carregar();
        }
        return instance;
    }

    // Salva o estado atual dos usuários no disco
    public void salvarTodos() {
        UsuarioDataManager.salvar(this);
    }

    // Retorna todos os usuários
    public Lista<Usuario> buscarTodos() {
        return usuarios;
    }

    // Adiciona um novo usuário
    public boolean adicionar(Usuario usuario) {
        if (usuario == null || buscarPorUsername(usuario.getUsername()) != null) {
            return false;
        }
        usuarios.adicionar(usuario);
        salvarTodos();
        return true;
    }

    // Busca um usuário por username (case-insensitive)
    public Usuario buscarPorUsername(String username) {
        if (username == null) {
            return null;
        }
        for (int i = 0; i < usuarios.tamanho(); i++) {
            Usuario usuario = usuarios.pegar(i);
            if (usuario.getUsername().equalsIgnoreCase(username.trim())) {
                return usuario;
            }
        }
        return null;
    }

    // Remove um usuário existente
    public boolean remover(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        Usuario encontrado = buscarPorUsername(usuario.getUsername());
        if (encontrado != null) {
            usuarios.remover(encontrado);
            salvarTodos();
            return true;
        }
        return false;
    }

    // Atualiza um usuário existente
    public boolean atualizar(String usernameOriginal, Usuario usuarioAtualizado) {
        if (usernameOriginal == null || usuarioAtualizado == null) {
            return false;
        }
        Usuario existente = buscarPorUsername(usernameOriginal);
        if (existente == null) {
            return false;
        }

        // Se o username foi alterado, checar se o novo username já está em uso
        if (!usernameOriginal.equalsIgnoreCase(usuarioAtualizado.getUsername().trim())) {
            if (buscarPorUsername(usuarioAtualizado.getUsername().trim()) != null) {
                return false;
            }
        }

        // Se mudou o perfil (Administrador vs Funcionario), substitui na lista
        if (existente.getTipo() != usuarioAtualizado.getTipo()) {
            usuarios.remover(existente);
            usuarios.adicionar(usuarioAtualizado);
        } else {
            existente.setUsername(usuarioAtualizado.getUsername().trim());
            existente.setPassword(usuarioAtualizado.getPassword().trim());
        }

        salvarTodos();
        return true;
    }

    // Busca um usuário por username e senha
    public Usuario buscarPorNamePassword(String username, String password) {
        for (int i = 0; i < usuarios.tamanho(); i++) {
            Usuario usuario = usuarios.pegar(i);
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscar(Usuario usuario) {
        return buscarPorNamePassword(usuario.getUsername(), usuario.getPassword());
    }
}
