package br.feevale.projetofinal.model.pagamento;

public class PagamentoPix implements FormaPagamento {

    @Override
    public boolean processar(double valor) {
        return valor > 0;
    }

    @Override
    public String getTipo() {
        return "PIX";
    }
}