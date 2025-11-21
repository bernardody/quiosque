package br.feevale.projetofinal.model.pedido;

import java.io.Serializable;

import br.feevale.projetofinal.model.cardapio.ItemCardapio;

public class ItemPedido implements Serializable {
    private ItemCardapio item;
    private int quantidade;

    public ItemPedido(ItemCardapio item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return item.calcularPreco() * quantidade;
    }

    public ItemCardapio getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}