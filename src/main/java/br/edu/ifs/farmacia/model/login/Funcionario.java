package br.edu.ifs.farmacia.model.login;

/**
 * Entidade Funcionário do sistema. Herda de Usuario (já serializável).
 *
 * @author Aluno
 */
public class Funcionario extends Usuario {
    
    private static final long serialVersionUID = 1L;

    public Funcionario(String username, String password) {
        super(username, password);
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.FUNCIONARIO;
    }
    
    @Override
    public String toString() {
        return super.toString() + " -> Restrição: " + this.getTipo() + '}';
    }
}
