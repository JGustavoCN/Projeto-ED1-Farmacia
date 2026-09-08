package br.edu.ifs.farmacia.util;

import br.edu.ifs.farmacia.model.Produto;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProdutoUtil {

    public static double totalEntrada(Lista<Produto> lista) {
        double total = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            total += (lista.pegar(i).getQuantidadeEstoque() * lista.pegar(i).getValorEntrada());
        }
        return total;
    }
    
    public static double totalSaida(Lista<Produto> lista) {
        double total = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            total += (lista.pegar(i).getQuantidadeEstoque() * lista.pegar(i).getValorSaida());
        }
        return total;
    }
    
    public static int totalQuantidade(Lista<Produto> lista) {
        int total = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            total += lista.pegar(i).getQuantidadeEstoque();
        }
        return total;
    }
    
    public static double totalLucro(Lista<Produto> lista) {
        return totalSaida(lista) - totalEntrada(lista);
    }
    
    public static Produto produtoMaisBarato(Lista<Produto> lista) {
        if (lista.estaVazia()) return null;
        Produto produto = lista.pegar(0);
        for (int i = 1; i < lista.tamanho(); i++) {
            if (lista.pegar(i).getValorSaida() < produto.getValorSaida()) {
                produto = lista.pegar(i); 
            }
        }
        return produto;
    }

    public static Object[] produtoToTableRow(Produto produto, int rowNum) {
        NumberFormat nf = new DecimalFormat("R$ #,##0.00");
        Object[] produtoToTableRow = new Object[]{
            false,
            rowNum,
            produto.getCodigo(),
            produto,
            produto.getMarca(),
            produto.getDescricao(),
            produto.getQuantidadeEstoque(),
            nf.format(produto.getValorEntrada()),
            nf.format(produto.getValorSaida())
        };
        return produtoToTableRow;
    }

    public static Object[] produtoToTableRowPriceEditor(Produto produto, int rowNum) {
        NumberFormat nf = new DecimalFormat("R$ #,##0.00");
        Object[] produtoToTableRow = new Object[]{
            rowNum,
            produto.getCodigo(),
            produto,
            produto.getMarca(),
            nf.format(produto.getValorSaida())
        };
        return produtoToTableRow;
    }

}
