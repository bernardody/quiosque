package br.feevale.projetofinal;

import br.feevale.projetofinal.controller.PersistenciaController;
import br.feevale.projetofinal.model.*;
import br.feevale.projetofinal.model.cardapio.*;
import br.feevale.projetofinal.model.pedido.Pedido;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

public class MainApplication extends Application {

    private static Estabelecimento estabelecimento;

    @Override
    public void start(Stage primaryStage) throws Exception {
        inicializarSistema();
        carregarPedidos();

        var url = getClass().getResource("/br/feevale/projetofinal/view/tela-inicial.fxml");

        if (url == null) {
            System.err.println("ERRO: Arquivo tela-inicial.fxml não encontrado!");
            throw new Exception("Arquivo FXML não encontrado");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Sistema de Quiosque - Burger Express");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void inicializarSistema() {
        estabelecimento = new Estabelecimento("Burger Express");

        estabelecimento.adicionarItemCardapio(
                new Lanche("L001", "Big Burger", "Hambúrguer grande com queijo", 25.90)
        );
        estabelecimento.adicionarItemCardapio(
                new Lanche("L002", "Cheese Burger", "Hambúrguer com muito queijo", 22.50)
        );
        estabelecimento.adicionarItemCardapio(
                new Lanche("L003", "X-Salada", "Hambúrguer com salada", 18.90)
        );

        estabelecimento.adicionarItemCardapio(
                new Bebida("B001", "Coca-Cola 500ml", "Refrigerante", 7.00)
        );
        estabelecimento.adicionarItemCardapio(
                new Bebida("B002", "Suco de Laranja", "Suco natural", 8.50)
        );
        estabelecimento.adicionarItemCardapio(
                new Bebida("B003", "Água 500ml", "Água mineral", 4.00)
        );

        estabelecimento.adicionarItemCardapio(
                new Acompanhamento("A001", "Batata Frita Grande", "Batata frita crocante", 12.00)
        );
        estabelecimento.adicionarItemCardapio(
                new Acompanhamento("A002", "Nuggets", "10 unidades", 15.00)
        );
        estabelecimento.adicionarItemCardapio(
                new Acompanhamento("A003", "Onion Rings", "Anéis de cebola", 13.50)
        );

        estabelecimento.adicionarItemCardapio(
                new Sobremesa("S001", "Sorvete de Chocolate", "Sorvete cremoso", 10.00)
        );
        estabelecimento.adicionarItemCardapio(
                new Sobremesa("S002", "Sundae de Morango", "Sorvete com calda", 12.00)
        );
        estabelecimento.adicionarItemCardapio(
                new Sobremesa("S003", "Torta de Maçã", "Torta quente", 14.00)
        );

        Combo combo1 = new Combo("C001", "Combo Big Burger", "Big Burger + Batata + Refri", 0.15);
        combo1.adicionarItem(estabelecimento.buscarItemPorCodigo("L001"));
        combo1.adicionarItem(estabelecimento.buscarItemPorCodigo("A001"));
        combo1.adicionarItem(estabelecimento.buscarItemPorCodigo("B001"));
        estabelecimento.adicionarItemCardapio(combo1);

        Combo combo2 = new Combo("C002", "Combo Cheese", "Cheese Burger + Nuggets + Suco", 0.10);
        combo2.adicionarItem(estabelecimento.buscarItemPorCodigo("L002"));
        combo2.adicionarItem(estabelecimento.buscarItemPorCodigo("A002"));
        combo2.adicionarItem(estabelecimento.buscarItemPorCodigo("B002"));
        estabelecimento.adicionarItemCardapio(combo2);
    }

    private void carregarPedidos() {
        List<Pedido> pedidosSalvos = PersistenciaController.carregarPedidos();
        for (Pedido pedido : pedidosSalvos) {
            estabelecimento.registrarPedido(pedido);
        }
    }

    public static Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public static void main(String[] args) {
        launch(args);
    }
}