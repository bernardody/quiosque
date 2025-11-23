package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.model.Estabelecimento;
import br.feevale.projetofinal.model.pedido.Pedido;
import br.feevale.projetofinal.model.pedido.StatusPedido;

import java.util.List;

public class GerenciamentoController {
    private final Estabelecimento estabelecimento;

    public GerenciamentoController(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Pedido> getTodosPedidos() {
        return estabelecimento.getTodosPedidos();
    }

    public List<Pedido> getPedidosPorStatus(StatusPedido status) {
        return estabelecimento.getPedidosPorStatus(status);
    }

    public void avancarStatusPedido(int numeroPedido) {
        Pedido pedido = estabelecimento.buscarPedidoPorNumero(numeroPedido);
        if (pedido != null) {
            pedido.avancarStatus();
            PersistenciaController.salvarPedidos(estabelecimento.getTodosPedidos());
        }
    }
}