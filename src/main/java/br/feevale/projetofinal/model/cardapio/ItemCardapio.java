package br.feevale.projetofinal.model.cardapio;

import java.io.Serializable;

public abstract class ItemCardapio implements Serializable {
    private final String codigo;
    private final String nome;
    private final String descricao;
    private final double preco;
    private boolean disponivel;

    public ItemCardapio(String codigo, String nome, String descricao, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = true;
    }

    public abstract String getCategoria();

    public double calcularPreco() {
        return this.preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}