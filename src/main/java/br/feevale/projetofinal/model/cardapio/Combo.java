package br.feevale.projetofinal.model.cardapio;

import br.feevale.projetofinal.model.cardapio.ItemCardapio;

import java.util.ArrayList;
import java.util.List;

public class Combo extends ItemCardapio {
    private List<ItemCardapio> itens;
    private double percentualDesconto;

    public Combo(String codigo, String nome, String descricao, double percentualDesconto) {
        super(codigo, nome, descricao, 0.0);
        this.itens = new ArrayList<>();
        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public String getCategoria() {
        return "Combo";
    }

    public void adicionarItem(ItemCardapio item) {
        if (!(item instanceof Combo)) {
            this.itens.add(item);
        }
    }

    @Override
    public double calcularPreco() {
        double total = 0;
        for (ItemCardapio item : itens) {
            total += item.calcularPreco();
        }
        return total * (1 - percentualDesconto);
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }
}