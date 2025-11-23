package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.model.Estabelecimento;
import br.feevale.projetofinal.model.cardapio.ItemCardapio;

import java.util.List;

public class CardapioController {
    private final Estabelecimento estabelecimento;

    public CardapioController(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<ItemCardapio> getItensDisponiveis() {
        return estabelecimento.getItensDisponiveis();
    }

    public List<ItemCardapio> getItensPorCategoria(String categoria) {
        return estabelecimento.getItensPorCategoria(categoria);
    }
}