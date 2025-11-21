package br.feevale.projetofinal.controller;

import br.feevale.projetofinal.model.*;
import br.feevale.projetofinal.model.cardapio.ItemCardapio;

import java.util.List;

public class CardapioController {
    private Estabelecimento estabelecimento;

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