package br.feevale.projetofinal.model.cardapio;

public class Bebida extends ItemCardapio {

    public Bebida(String codigo, String nome, String descricao, double preco) {
        super(codigo, nome, descricao, preco);
    }

    @Override
    public String getCategoria() {
        return "Bebida";
    }
}