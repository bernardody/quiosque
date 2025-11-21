package br.feevale.projetofinal.model.pagamento;

public class PagamentoDinheiro implements FormaPagamento {

    @Override
    public boolean processar(double valor) {
        return valor > 0;
    }

    @Override
    public String getTipo() {
        return "Dinheiro";
    }
}