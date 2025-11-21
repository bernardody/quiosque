package br.feevale.projetofinal.model.pagamento;

import java.io.Serializable;

public class PagamentoPix implements FormaPagamento, Serializable {

    @Override
    public boolean processar(double valor) {
        return valor > 0;
    }

    @Override
    public String getTipo() {
        return "PIX";
    }
}