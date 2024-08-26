package br.edu.ifs.farmacia.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Aluno
 */
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    private UUID id;
    private String nome;
    private int codigo;
    private String descricao;
    private String marca;
    private double valorEntrada;
    private double valorSaida;
    private int quantidaDeEstoque;

    public Produto() {
    }

    public Produto(String nome, int codigo, String descricao, String marca, double valorEntrada, double valorSaida, int quantidaDeEstoque) {
        this.id = UUID.nameUUIDFromBytes((nome.trim()+marca.trim()).trim().toUpperCase().getBytes());
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.marca = marca;
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.quantidaDeEstoque = quantidaDeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(double valorSaida) {
        this.valorSaida = valorSaida;
    }

    public int getQuantidadeEstoque() {
        return quantidaDeEstoque;
    }

    public void setQuantidadeEstoque(int quantidaDeEstoque) {
        this.quantidaDeEstoque = quantidaDeEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + this.codigo;
        hash = 17 * hash + Objects.hashCode(this.marca);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!this.nome.equalsIgnoreCase(other.nome)) {
            return false;
        }
        if (!this.marca.equalsIgnoreCase(other.marca)) {
            return false;
        }
        return this.id.compareTo(other.id) == 0;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", codigo=" + codigo + ", descricao=" + descricao + ", marca=" + marca + ", valorEntrada=" + valorEntrada + ", valorSaida=" + valorSaida + ", quantidaDeEstoque=" + quantidaDeEstoque + '}';
    }

}
