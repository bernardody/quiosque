package br.feevale.projetofinal.model.pagamento;

public class PagamentoCartao implements FormaPagamento {
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