package br.edu.ifs.farmacia.model.login;

/**
 * Entidade Administrador do sistema. Herda de Usuario (já serializável).
 *
 * @author Aluno
 */
public class Administrador extends Usuario {
    
    private static final long serialVersionUID = 1L;

    public Administrador(String username, String password) {
        super(username, password);
    }
    
    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.ADMINISTRADOR;
    }

    @Override
    public String toString() {
        return super.toString() + " -> Restrição: " + this.getTipo() + '}';
    }
}
