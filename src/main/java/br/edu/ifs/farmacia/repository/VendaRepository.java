package br.edu.ifs.farmacia.repository;

import br.edu.ifs.farmacia.model.Venda;
import br.edu.ifs.farmacia.persistence.VendaDataManager;
import br.edu.ifs.farmacia.util.Lista;
import br.edu.ifs.farmacia.util.ProdutoNaoPodeSerVendidoException;
import br.edu.ifs.farmacia.util.VendaJaExisteException;
import br.edu.ifs.farmacia.util.VendaNaoEncontradoException;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Aluno
 */
public class VendaRepository implements Serializable {

    private static final long serialVersionUID = 1L;
    private Lista<Venda> vendas;
    
    public Venda buscarPorCodigo(UUID id) throws VendaNaoEncontradoException {
        for (int i = 0; i < vendas.tamanho(); i++) {
            if (vendas.pegar(i).getId().compareTo(id) == 0) {
                return vendas.pegar(i);
            }
        }
        throw new VendaNaoEncontradoException("Venda com id " + id + " não encontrado.");
    }

    public void salvarTodos(){
        VendaDataManager.salvar(this);
    }
    
    public boolean salvar(Venda venda) throws VendaJaExisteException {
        if (vendas.contem(venda) ) {
            throw new VendaJaExisteException("Venda com id " + venda.getId() + " já existe.");
        }
        return vendas.adicionar(venda);
    }

    public Lista<Venda> buscarTodos() {
        return vendas;
    }
    
    public Lista<Venda> buscarTodosOrdenadoPorNome() {
        Lista<Venda> vendasOrdenados = new Lista<>();
        // Adiciona todos os vendas na lista de vendasOrdenados
        for (int i = 0; i < vendas.tamanho(); i++) {
            vendasOrdenados.adicionarUltimo(vendas.pegar(i));
        }

        // Ordena a lista de vendas por nome
        vendasOrdenados.ordenar((Venda p1, Venda p2) -> p1.getProduto().getNome().compareToIgnoreCase(p2.getProduto().getNome()));

        return vendasOrdenados;
    }
    
    private VendaRepository() {
        vendas = new Lista<>();
    }

    public static VendaRepository createEmpty() {
        return new VendaRepository();
    }
    
    public static VendaRepository getInstance() {
        if (instance == null) {
            instance = VendaDataManager.carregar();
        }
        
        return instance;
    }
    private static VendaRepository instance;
}
