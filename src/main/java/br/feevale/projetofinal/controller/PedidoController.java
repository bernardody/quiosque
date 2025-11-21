package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.MainApplication;
import br.feevale.projetofinal.model.*;
import br.feevale.projetofinal.model.cardapio.ItemCardapio;
import br.feevale.projetofinal.model.pagamento.FormaPagamento;
import br.feevale.projetofinal.model.pedido.ItemPedido;
import br.feevale.projetofinal.model.pedido.Pedido;

import java.util.List;

public class PedidoController {
    private Estabelecimento estabelecimento;
    private Pedido pedidoAtual;

    public PedidoController(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void iniciarNovoPedido() {
        this.pedidoAtual = estabelecimento.criarNovoPedido();
    }

    public boolean adicionarItem(String codigoItem, int quantidade) {
        ItemCardapio item = estabelecimento.buscarItemPorCodigo(codigoItem);
        if (item != null) {
            pedidoAtual.adicionarItem(item, quantidade);
            return true;
        }
        return false;
    }

    public boolean removerItem(String codigoItem) {
        return pedidoAtual.removerItem(codigoItem);
    }

    public List<ItemPedido> getItensPedidoAtual() {
        return pedidoAtual.getItens();
    }

    public double getValorTotal() {
        return pedidoAtual.calcularTotal();
    }

    public boolean finalizarPedido(FormaPagamento pagamento) {
        if (pedidoAtual.processarPagamento(pagamento)) {
            estabelecimento.registrarPedido(pedidoAtual);

            PersistenciaController.salvar(MainApplication.getEstabelecimento());

            pedidoAtual = null;
            return true;
        }
        return false;
    }

    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
}