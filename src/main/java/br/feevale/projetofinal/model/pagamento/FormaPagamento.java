package br.feevale.projetofinal.model.pagamento;

public interface FormaPagamento {
    boolean processar(double valor);
    String getTipo();
}