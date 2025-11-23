package br.feevale.projetofinal.view;

import br.feevale.projetofinal.controller.PedidoController;
import br.feevale.projetofinal.model.pedido.ItemPedido;
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
import java.util.List;

public class TelaResumoController {

    @FXML
    private Label lblNumeroPedido;
    @FXML
    private VBox containerItens;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnRealizarPagamento;

    private PedidoController pedidoController;

    public void setPedidoController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
        carregarResumo();
    }

    private void carregarResumo() {
        lblNumeroPedido.setText("Pedido #" + pedidoController.getPedidoAtual().getNumero());

        List<ItemPedido> itens = pedidoController.getItensPedidoAtual();
        containerItens.getChildren().clear();

        for (ItemPedido itemPedido : itens) {
            VBox itemCard = criarCardItem(itemPedido);
            containerItens.getChildren().add(itemCard);
        }

        lblTotal.setText(String.format("R$ %.2f", pedidoController.getValorTotal()));
    }

    private VBox criarCardItem(ItemPedido itemPedido) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: #DC143C; -fx-padding: 10; -fx-background-radius: 5;");

        Label lblNome = new Label(itemPedido.getItem().getNome());
        lblNome.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-fill: #FFD700;");

        HBox info = new HBox(10);

        Label lblQtd = new Label("Quantidade: " + itemPedido.getQuantidade());
        lblQtd.setStyle("-fx-text-fill: #FFD700;");

        Label lblSubtotal = new Label(String.format("R$ %.2f", itemPedido.calcularSubtotal()));
        lblSubtotal.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold; -fx-font-size: 14;");

        info.getChildren().addAll(lblQtd, lblSubtotal);
        HBox.setHgrow(lblSubtotal, Priority.ALWAYS);
        lblSubtotal.setMaxWidth(Double.MAX_VALUE);
        lblSubtotal.setAlignment(Pos.CENTER_RIGHT);

        card.getChildren().addAll(lblNome, info);
        return card;
    }

    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-cardapio.fxml"));
            Parent root = loader.load();

            TelaCardapioController controller = loader.getController();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRealizarPagamento(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-pagamento.fxml"));
            Parent root = loader.load();

            TelaPagamentoController controller = loader.getController();
            controller.setPedidoController(pedidoController);

            Stage stage = (Stage) btnRealizarPagamento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}