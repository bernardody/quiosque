package br.feevale.projetofinal.view;

import br.feevale.projetofinal.MainApplication;
import br.feevale.projetofinal.controller.GerenciamentoController;
import br.feevale.projetofinal.model.pedido.Pedido;
import br.feevale.projetofinal.model.pedido.StatusPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaGerenciamentoController {

    @FXML
    private VBox containerPedidos;
    @FXML
    private Button btnTodos;
    @FXML
    private Button btnPagos;
    @FXML
    private Button btnEmPreparo;
    @FXML
    private Button btnProntos;
    @FXML
    private Button btnEntregues;
    @FXML
    private Button btnVoltar;

    private GerenciamentoController gerenciamentoController;

    @FXML
    public void initialize() {
        gerenciamentoController = new GerenciamentoController(MainApplication.getEstabelecimento());
        carregarPedidos(null);
    }

    private void carregarPedidos(StatusPedido status) {
        containerPedidos.getChildren().clear();
        resetarEstiloBotoes();

        List<Pedido> pedidos;
        if (status == null) {
            pedidos = gerenciamentoController.getTodosPedidos();
            destacarBotao(btnTodos);
        } else {
            pedidos = gerenciamentoController.getPedidosPorStatus(status);
            destacarBotaoPorStatus(status);
        }

        if (pedidos.isEmpty()) {
            Label lblVazio = new Label("Nenhum pedido encontrado");
            lblVazio.setStyle("-fx-text-fill: #DC143C; -fx-font-style: italic; -fx-font-size: 14;");
            containerPedidos.getChildren().add(lblVazio);
        } else {
            for (Pedido pedido : pedidos) {
                VBox card = criarCardPedido(pedido);
                containerPedidos.getChildren().add(card);
            }
        }
    }

    private VBox criarCardPedido(Pedido pedido) {
        VBox card = new VBox(8);
        card.setStyle("-fx-background-color: #DC143C; -fx-padding: 15; -fx-background-radius: 5; -fx-cursor: hand;");

        HBox linha1 = new HBox(10);
        linha1.setAlignment(Pos.CENTER_LEFT);

        Label lblNumero = new Label("Pedido #" + String.format("%03d", pedido.getNumero()));
        lblNumero.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #FFD700;");

        Label lblStatus = new Label(pedido.getStatus().toString());
        lblStatus.setStyle("-fx-font-size: 12; -fx-text-fill: #FFD700; -fx-background-color: #8B0000; -fx-padding: 3 8 3 8; -fx-background-radius: 3;");

        HBox.setHgrow(lblNumero, Priority.ALWAYS);
        linha1.getChildren().addAll(lblNumero, lblStatus);

        HBox linha2 = new HBox(10);
        linha2.setAlignment(Pos.CENTER_LEFT);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Label lblHora = new Label(pedido.getDataHora().format(formatter));
        lblHora.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 14;");

        Label lblTotal = new Label(String.format("R$ %.2f", pedido.calcularTotal()));
        lblTotal.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold; -fx-font-size: 16;");

        HBox.setHgrow(lblHora, Priority.ALWAYS);
        linha2.getChildren().addAll(lblHora, lblTotal);

        card.getChildren().addAll(linha1, linha2);

        card.setOnMouseClicked(e -> abrirDetalhesPedido(pedido));

        return card;
    }

    private void abrirDetalhesPedido(Pedido pedido) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-detalhes-pedido.fxml"));
            Parent root = loader.load();

            TelaDetalhesPedidoController controller = loader.getController();
            controller.setPedido(pedido);

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetarEstiloBotoes() {
        String estiloInativo = "-fx-background-color: #FFD700; -fx-text-fill: #DC143C;";
        btnTodos.setStyle(estiloInativo);
        btnPagos.setStyle(estiloInativo);
        btnEmPreparo.setStyle(estiloInativo);
        btnProntos.setStyle(estiloInativo);
        btnEntregues.setStyle(estiloInativo);
    }

    private void destacarBotao(Button botao) {
        botao.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #DC143C; -fx-font-weight: bold;");
    }

    private void destacarBotaoPorStatus(StatusPedido status) {
        switch (status) {
            case PAGO:
                destacarBotao(btnPagos);
                break;
            case EM_PREPARO:
                destacarBotao(btnEmPreparo);
                break;
            case PRONTO:
                destacarBotao(btnProntos);
                break;
            case ENTREGUE:
                destacarBotao(btnEntregues);
                break;
        }
    }

    @FXML
    private void onFiltrarTodos(ActionEvent event) {
        carregarPedidos(null);
    }

    @FXML
    private void onFiltrarPagos(ActionEvent event) {
        carregarPedidos(StatusPedido.PAGO);
    }

    @FXML
    private void onFiltrarEmPreparo(ActionEvent event) {
        carregarPedidos(StatusPedido.EM_PREPARO);
    }

    @FXML
    private void onFiltrarProntos(ActionEvent event) {
        carregarPedidos(StatusPedido.PRONTO);
    }

    @FXML
    private void onFiltrarEntregues(ActionEvent event) {
        carregarPedidos(StatusPedido.ENTREGUE);
    }

    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-inicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}