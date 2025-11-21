package br.feevale.projetofinal.model;

import br.feevale.projetofinal.model.cardapio.ItemCardapio;
import br.feevale.projetofinal.model.pedido.Pedido;
import br.feevale.projetofinal.model.pedido.StatusPedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estabelecimento implements Serializable {
    private String nome;
    private List<ItemCardapio> cardapio;
    private List<Pedido> pedidos;

    public Estabelecimento(String nome) {
        this.nome = nome;
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void adicionarItemCardapio(ItemCardapio item) {
        cardapio.add(item);
    }

    public ItemCardapio buscarItemPorCodigo(String codigo) {
        for (ItemCardapio item : cardapio) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    public List<ItemCardapio> getItensDisponiveis() {
        return cardapio.stream()
                .filter(ItemCardapio::isDisponivel)
                .collect(Collectors.toList());
    }

    public List<ItemCardapio> getItensPorCategoria(String categoria) {
        return cardapio.stream()
                .filter(item -> item.getCategoria().equals(categoria))
                .collect(Collectors.toList());
    }

    public Pedido criarNovoPedido() {
        return new Pedido();
    }

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> getTodosPedidos() {
        return pedidos;
    }

    public List<Pedido> getPedidosPorStatus(StatusPedido status) {
        return pedidos.stream()
                .filter(pedido -> pedido.getStatus() == status)
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }
}