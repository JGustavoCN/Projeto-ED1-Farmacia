package br.edu.ifs.farmacia.repository;

import br.edu.ifs.farmacia.model.Produto;
import br.edu.ifs.farmacia.persistence.ProdutoDataManager;
import br.edu.ifs.farmacia.util.Lista;
import br.edu.ifs.farmacia.util.ProdutoJaExisteException;
import br.edu.ifs.farmacia.util.ProdutoNaoEncontradoException;
import java.io.Serializable;

/**
 *
 * @author Aluno
 */
public class ProdutoRepository implements Serializable {

    private static final long serialVersionUID = 1L;
    private Lista<Produto> produtos;

    public Produto buscarPorCodigo(int codigo) throws ProdutoNaoEncontradoException {
        for (int i = 0; i < produtos.tamanho(); i++) {
            if (produtos.pegar(i).getCodigo() == codigo) {
                return produtos.pegar(i);
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com código " + codigo + " não encontrado.");
    }

    public void salvarTodos() {
        ProdutoDataManager.salvar(this);
    }

    public boolean salvar(Produto produto) throws ProdutoJaExisteException {
        if (produtos.contem(produto)) {
            throw new ProdutoJaExisteException("Produto com código " + produto.getCodigo() + " ou com o id " + produto.getId() + " já existe.");
        }
        return produtos.adicionar(produto);
    }

    public Lista<Produto> buscarTodos() {
        return produtos;
    }

    public void atualizar(Produto dataEdit) throws ProdutoNaoEncontradoException {

        Produto produto = buscarPorCodigo(dataEdit.getCodigo());
        produto.setValorEntrada(dataEdit.getValorEntrada());
        produto.setValorSaida(dataEdit.getValorSaida());
        produto.setQuantidadeEstoque(dataEdit.getQuantidadeEstoque());

    }

    public void atualizarPrecoDeTodos(Lista<Produto> lista, double valor) throws ProdutoNaoEncontradoException {
        for (int i = 0; i < lista.tamanho(); i++) {
            Produto produto = buscarPorCodigo(lista.pegar(i).getCodigo());
            produto.setValorSaida(lista.pegar(i).getValorSaida() + valor);
        }
    }

    public void atualizarPreco(Produto produto, double valor) throws ProdutoNaoEncontradoException {
        Produto produtoEncontrado = buscarPorCodigo(produto.getCodigo());
        produto.setValorSaida(produtoEncontrado.getValorSaida() + valor);
    }

    public Lista<Produto> buscarTodosOrdenadoPorNome() {
        Lista<Produto> produtosOrdenados = new Lista<>();
        // Adiciona todos os produtos na lista de produtosOrdenados
        for (int i = 0; i < produtos.tamanho(); i++) {
            produtosOrdenados.adicionarUltimo(produtos.pegar(i));
        }

        // Ordena a lista de produtos por nome
        produtosOrdenados.ordenar((Produto p1, Produto p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()));

        return produtosOrdenados;
    }

    private ProdutoRepository() {
        produtos = new Lista<>();
        // Exemplo de dados para criar um objeto Produto
        String nome = "Paracetamol";
        int codigo = 123;
        String descricao = "Analgésico e antipirético usado para aliviar dor e febre.";
        String marca = "Farmácia ABC";
        double valorEntrada = 10;
        double valorSaida = 20;
        int quantidadeEstoque = 10;

        // Cria o objeto Produto
        Produto produto = new Produto(nome, codigo, descricao, marca, valorEntrada, valorSaida, quantidadeEstoque);

        produtos.adicionar(produto);
    }

    public static ProdutoRepository createEmpty() {
        return new ProdutoRepository();
    }

    public static ProdutoRepository getInstance() {
        if (instance == null) {
            instance = ProdutoDataManager.carregar();
        }

        return instance;
    }
    private static ProdutoRepository instance;
}
