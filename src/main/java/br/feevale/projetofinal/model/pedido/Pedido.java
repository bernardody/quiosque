package br.feevale.projetofinal.model.pedido;

import br.feevale.projetofinal.model.cardapio.ItemCardapio;
import br.feevale.projetofinal.model.pagamento.FormaPagamento;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    private static int contadorNumero = 1;

    private int numero;
    private LocalDateTime dataHora;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private FormaPagamento formaPagamento;

    public Pedido() {
        this.numero = contadorNumero++;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public void adicionarItem(ItemCardapio item, int quantidade) {
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getItem().getCodigo().equals(item.getCodigo())) {
                itemPedido.setQuantidade(itemPedido.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new ItemPedido(item, quantidade));
    }

    public boolean removerItem(String codigoItem) {
        return itens.removeIf(itemPedido ->
                itemPedido.getItem().getCodigo().equals(codigoItem));
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido itemPedido : itens) {
            total += itemPedido.calcularSubtotal();
        }
        return total;
    }

    public boolean processarPagamento(FormaPagamento formaPagamento) {
        if (formaPagamento.processar(calcularTotal())) {
            this.formaPagamento = formaPagamento;
            this.status = StatusPedido.PAGO;
            return true;
        }
        return false;
    }

    public void avancarStatus() {
        switch (status) {
            case AGUARDANDO_PAGAMENTO: status = StatusPedido.PAGO; break;
            case PAGO: status = StatusPedido.EM_PREPARO; break;
            case EM_PREPARO: status = StatusPedido.PRONTO; break;
            case PRONTO: status = StatusPedido.ENTREGUE; break;
            case ENTREGUE: status = StatusPedido.ENTREGUE; break;
        }
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}