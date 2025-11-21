package br.feevale.projetofinal.view;

import br.feevale.projetofinal.MainApplication;
import br.feevale.projetofinal.controller.CardapioController;
import br.feevale.projetofinal.controller.PedidoController;
import br.feevale.projetofinal.model.cardapio.ItemCardapio;
import br.feevale.projetofinal.model.pedido.ItemPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class TelaCardapioController {

    @FXML private VBox containerProdutos;
    @FXML private VBox containerCarrinho;
    @FXML private Label lblTotal;
    @FXML private Button btnFinalizarPedido;
    @FXML private Button btnVoltar;
    @FXML private Button btnTodos;
    @FXML private Button btnLanches;
    @FXML private Button btnBebidas;
    @FXML private Button btnAcompanhamentos;
    @FXML private Button btnSobremesas;
    @FXML private Button btnCombos;

    private CardapioController cardapioController;
    private PedidoController pedidoController;
    private String categoriaAtual = "Todos";

    @FXML
    public void initialize() {
        cardapioController = new CardapioController(MainApplication.getEstabelecimento());
        pedidoController = new PedidoController(MainApplication.getEstabelecimento());

        pedidoController.iniciarNovoPedido();

        carregarProdutos("Todos");

        atualizarCarrinho();
    }

    private void carregarProdutos(String categoria) {
        containerProdutos.getChildren().clear();
        categoriaAtual = categoria;

        resetarEstiloBotoes();
        destacarBotaoAtivo(categoria);

        List<ItemCardapio> produtos;
        if (categoria.equals("Todos")) {
            produtos = cardapioController.getItensDisponiveis();
        } else {
            produtos = cardapioController.getItensPorCategoria(categoria);
        }

        for (ItemCardapio produto : produtos) {
            VBox card = criarCardProduto(produto);
            containerProdutos.getChildren().add(card);
        }
    }

    private VBox criarCardProduto(ItemCardapio produto) {
        VBox card = new VBox(8);
        card.setStyle("-fx-background-color: #FFD700; -fx-padding: 10; -fx-background-radius: 5;");
        card.setPrefWidth(270);

        Label lblNome = new Label(produto.getNome());
        lblNome.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #8B0000;");

        Label lblDescricao = new Label(produto.getDescricao());
        lblDescricao.setStyle("-fx-text-fill: #DC143C; -fx-font-size: 11;");
        lblDescricao.setWrapText(true);

        HBox rodape = new HBox(10);
        rodape.setAlignment(Pos.CENTER_LEFT);

        Label lblPreco = new Label(String.format("R$ %.2f", produto.getPreco()));
        lblPreco.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: #8B0000;");

        Button btnAdicionar = new Button("+");
        btnAdicionar.setStyle("-fx-background-color: #DC143C; -fx-text-fill: #FFD700; -fx-font-weight: bold; -fx-font-size: 16;");
        btnAdicionar.setPrefSize(30, 30);
        btnAdicionar.setOnAction(e -> adicionarAoCarrinho(produto));

        HBox.setHgrow(lblPreco, Priority.ALWAYS);
        rodape.getChildren().addAll(lblPreco, btnAdicionar);

        card.getChildren().addAll(lblNome, lblDescricao, rodape);
        return card;
    }

    private void adicionarAoCarrinho(ItemCardapio produto) {
        pedidoController.adicionarItem(produto.getCodigo(), 1);
        atualizarCarrinho();
    }

    private void atualizarCarrinho() {
        containerCarrinho.getChildren().clear();

        List<ItemPedido> itens = pedidoController.getItensPedidoAtual();

        if (itens == null || itens.isEmpty()) {
            Label lblVazio = new Label("Carrinho vazio");
            lblVazio.setStyle("-fx-text-fill: #95a5a6; -fx-font-style: italic;");
            containerCarrinho.getChildren().add(lblVazio);
        } else {
            for (ItemPedido itemPedido : itens) {
                VBox itemCard = criarCardCarrinho(itemPedido);
                containerCarrinho.getChildren().add(itemCard);
            }
        }

        double total = pedidoController.getValorTotal();
        lblTotal.setText(String.format("R$ %.2f", total));
    }

    private VBox criarCardCarrinho(ItemPedido itemPedido) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: #2c3e50; -fx-padding: 8; -fx-background-radius: 5;");

        Label lblNome = new Label(itemPedido.getItem().getNome());
        lblNome.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        lblNome.setWrapText(true);

        HBox info = new HBox(10);
        info.setAlignment(Pos.CENTER_LEFT);

        Label lblQtd = new Label("Qtd: " + itemPedido.getQuantidade());
        lblQtd.setStyle("-fx-text-fill: #ecf0f1;");

        Label lblSubtotal = new Label(String.format("R$ %.2f", itemPedido.calcularSubtotal()));
        lblSubtotal.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

        info.getChildren().addAll(lblQtd, lblSubtotal);

        Button btnRemover = new Button("Remover");
        btnRemover.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10;");
        btnRemover.setOnAction(e -> {
            pedidoController.removerItem(itemPedido.getItem().getCodigo());
            atualizarCarrinho();
        });

        card.getChildren().addAll(lblNome, info, btnRemover);
        return card;
    }

    private void resetarEstiloBotoes() {
        String estiloInativo = "-fx-background-color: #95a5a6; -fx-text-fill: white;";
        btnTodos.setStyle(estiloInativo);
        btnLanches.setStyle(estiloInativo);
        btnBebidas.setStyle(estiloInativo);
        btnAcompanhamentos.setStyle(estiloInativo);
        btnSobremesas.setStyle(estiloInativo);
        btnCombos.setStyle(estiloInativo);
    }

    private void destacarBotaoAtivo(String categoria) {
        String estiloAtivo = "-fx-background-color: #3498db; -fx-text-fill: white;";
        switch (categoria) {
            case "Todos": btnTodos.setStyle(estiloAtivo); break;
            case "Lanche": btnLanches.setStyle(estiloAtivo); break;
            case "Bebida": btnBebidas.setStyle(estiloAtivo); break;
            case "Acompanhamento": btnAcompanhamentos.setStyle(estiloAtivo); break;
            case "Sobremesa": btnSobremesas.setStyle(estiloAtivo); break;
            case "Combo": btnCombos.setStyle(estiloAtivo); break;
        }
    }

    @FXML
    private void onFiltrarTodos(ActionEvent event) {
        carregarProdutos("Todos");
    }

    @FXML
    private void onFiltrarLanches(ActionEvent event) {
        carregarProdutos("Lanche");
    }

    @FXML
    private void onFiltrarBebidas(ActionEvent event) {
        carregarProdutos("Bebida");
    }

    @FXML
    private void onFiltrarAcompanhamentos(ActionEvent event) {
        carregarProdutos("Acompanhamento");
    }

    @FXML
    private void onFiltrarSobremesas(ActionEvent event) {
        carregarProdutos("Sobremesa");
    }

    @FXML
    private void onFiltrarCombos(ActionEvent event) {
        carregarProdutos("Combo");
    }

    @FXML
    private void onFinalizarPedido(ActionEvent event) {
        if (pedidoController.getItensPedidoAtual() == null ||
                pedidoController.getItensPedidoAtual().isEmpty()) {
            System.out.println("Carrinho vazio! Adicione itens antes de finalizar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-resumo.fxml"));
            Parent root = loader.load();

            TelaResumoController controller = loader.getController();
            controller.setPedidoController(pedidoController);

            Stage stage = (Stage) btnFinalizarPedido.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar tela de resumo: " + e.getMessage());
        }
    }

    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-inicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao voltar para tela inicial: " + e.getMessage());
        }
    }
}