package br.edu.ifs.farmacia.model.login;

/**
 * Níveis de acesso de usuário do sistema.
 * Enums nativamente implementam Serializable.
 *
 * @author Aluno
 */
public enum TipoUsuario {
    
    FUNCIONARIO("Funcionário", "Acesso básico, permitido apenas a funcionalidades padrão."),
    ADMINISTRADOR("Administrador", "Acesso completo, com permissão para gerenciar usuários e configurar o sistema.");

    private final String nome;
    private final String descricao;

    TipoUsuario(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome + ": " + descricao;
    }
}