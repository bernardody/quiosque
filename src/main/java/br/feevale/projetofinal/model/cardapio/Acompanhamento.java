package br.feevale.projetofinal.model.cardapio;

import br.feevale.projetofinal.model.cardapio.ItemCardapio;

public class Acompanhamento extends ItemCardapio {

    public Acompanhamento(String codigo, String nome, String descricao, double preco) {
        super(codigo, nome, descricao, preco);
    }

    @Override
    public String getCategoria() {
        return "Acompanhamento";
    }
}