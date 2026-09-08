package br.edu.ifs.farmacia.util;

import br.edu.ifs.farmacia.model.Venda;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VendaUtil {
    
    public static double totalVendido(Lista<Venda> lista) {
        double total = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            total += lista.pegar(i).getTotal();
        }
        return total;
    }
    
    public static int totalQuantidade(Lista<Venda> lista) {
        int total = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            total += lista.pegar(i).getQuantidade();
        }
        return total;
    }
    
    public static Object[] vendaToTableRow(Venda venda, int rowNum){
        NumberFormat nf = new DecimalFormat("R$ #,##0.00");
        Object[] vendaToTableRow = new Object[]{
            venda,
            rowNum, 
            venda.getProduto().getCodigo(),
            venda.getProduto().getNome(), 
            venda.getProduto().getMarca(),
            nf.format(venda.getProduto().getValorSaida()),
            venda.getQuantidade(),
            nf.format(venda.getTotal())
        };
        return vendaToTableRow;
    }
}
