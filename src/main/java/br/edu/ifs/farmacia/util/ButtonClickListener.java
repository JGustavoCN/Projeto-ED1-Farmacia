package br.edu.ifs.farmacia.util;

/**
 * Interface funcional para escutas de cliques em botões.
 *
 * @author Aluno
 * @param <T> Tipo dos elementos da ação
 */
@FunctionalInterface
public interface ButtonClickListener<T> {
    
    @SuppressWarnings("unchecked")
    void onClick(T... source);
}
