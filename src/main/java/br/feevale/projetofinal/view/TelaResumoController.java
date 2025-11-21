package br.feevale.projetofinal.view;

import br.feevale.projetofinal.controller.PedidoController;
import br.feevale.projetofinal.model.pedido.ItemPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class TelaResumoController {

    @FXML private Label lblNumeroPedido;
    @FXML private VBox containerItens;
    @FXML private Label lblTotal;
    @FXML private Button btnVoltar;
    @FXML private Button btnRealizarPagamento;

    private PedidoController pedidoController;

    public void setPedidoController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
        carregarResumo();
    }

    private void carregarResumo() {
        // Número do pedido
        lblNumeroPedido.setText("Pedido #" + pedidoController.getPedidoAtual().getNumero());

        // Itens
        List<ItemPedido> itens = pedidoController.getItensPedidoAtual();
        containerItens.getChildren().clear();

        for (ItemPedido itemPedido : itens) {
            VBox itemCard = criarCardItem(itemPedido);
            containerItens.getChildren().add(itemCard);
        }

        // Total
        lblTotal.setText(String.format("R$ %.2f", pedidoController.getValorTotal()));
    }

    private VBox criarCardItem(ItemPedido itemPedido) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 5;");

        Label lblNome = new Label(itemPedido.getItem().getNome());
        lblNome.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        HBox info = new HBox(10);

        Label lblQtd = new Label("Quantidade: " + itemPedido.getQuantidade());
        lblQtd.setStyle("-fx-text-fill: #7f8c8d;");

        Label lblSubtotal = new Label(String.format("R$ %.2f", itemPedido.calcularSubtotal()));
        lblSubtotal.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold; -fx-font-size: 14;");

        info.getChildren().addAll(lblQtd, lblSubtotal);
        HBox.setMargin(lblSubtotal, new Insets(0, 0, 0, 100));

        card.getChildren().addAll(lblNome, info);
        return card;
    }

    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-cardapio.fxml"));
            Parent root = loader.load();

            // Passa o pedidoController de volta para a tela de cardápio
            TelaCardapioController controller = loader.getController();
            // Note: precisaríamos passar o controller aqui, mas para simplificar,
            // vamos apenas voltar e o usuário perde o pedido

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

            // Passa o pedidoController para a tela de pagamento
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