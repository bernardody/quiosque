package br.feevale.projetofinal.model.pagamento;

import java.io.Serializable;

public class PagamentoCartao implements FormaPagamento, Serializable {
    private String tipo;

    public PagamentoCartao(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean processar(double valor) {
        return valor > 0;
    }

    @Override
    public String getTipo() {
        return "Cartão " + tipo;
    }
}