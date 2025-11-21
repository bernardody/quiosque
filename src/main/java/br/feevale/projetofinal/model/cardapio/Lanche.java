package br.feevale.projetofinal.model.cardapio;

public class Lanche extends ItemCardapio {

    public Lanche(String codigo, String nome, String descricao, double preco) {
        super(codigo, nome, descricao, preco);
    }

    @Override
    public String getCategoria() {
        return "Lanche";
    }
}