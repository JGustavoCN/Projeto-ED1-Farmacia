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
        if (usuario == null || usuarios.contem(usuario)) {
            return false;
        }
        usuarios.adicionar(usuario);
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
