package br.edu.ifs.farmacia.util;

import java.io.Serializable;

/**
 *
 * @author José Gustavo
 * @param <E> Tipo do Nó
 */
public class No<E> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private E elemento;
    private No<E> proximo;

    public No(E elemento, No<E> proximo) {
        this.elemento = elemento;
        this.proximo = proximo;
    }

    public No(E elemento) {
        this.elemento = elemento;
    }
    
    
    public No<E> recortar(){
        No<E> no = new No<>(elemento, proximo);
        this.setElemento(null);
        this.setProximo(null);
        return no;
    }

    public boolean temProximo(){
        return proximo != null;
    }
    
    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public No<E> getProximo() {
        return proximo;
    }

    public void setProximo(No<E> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("No{");
        sb.append("elemento=").append(elemento);
        sb.append(", proximo=").append(proximo);
        sb.append('}');
        return sb.toString();
    }

}
