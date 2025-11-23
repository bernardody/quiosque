package br.feevale.projetofinal.view;

import br.feevale.projetofinal.MainApplication;
import br.feevale.projetofinal.controller.GerenciamentoController;
import br.feevale.projetofinal.model.pedido.ItemPedido;
import br.feevale.projetofinal.model.pedido.Pedido;
import br.feevale.projetofinal.model.pedido.StatusPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDetalhesPedidoController {

    @FXML
    private Label lblNumeroPedido;
    @FXML
    private Label lblStatus;
    @FXML
    private VBox containerItens;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblFormaPagamento;
    @FXML
    private Button btnAvancarStatus;
    @FXML
    private Button btnVoltar;

    private Pedido pedido;
    private GerenciamentoController gerenciamentoController;

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.gerenciamentoController = new GerenciamentoController(MainApplication.getEstabelecimento());
        carregarDetalhes();
    }

    private void carregarDetalhes() {
        lblNumeroPedido.setText("Pedido #" + String.format("%03d", pedido.getNumero()));
        lblStatus.setText(pedido.getStatus().toString());
        lblTotal.setText(String.format("Total: R$ %.2f", pedido.calcularTotal()));

        if (pedido.getFormaPagamento() != null) {
            lblFormaPagamento.setText("Pagamento: " + pedido.getFormaPagamento().getTipo());
        }

        containerItens.getChildren().clear();
        for (ItemPedido itemPedido : pedido.getItens()) {
            VBox itemCard = criarCardItem(itemPedido);
            containerItens.getChildren().add(itemCard);
        }

        if (pedido.getStatus() == StatusPedido.ENTREGUE || pedido.getStatus() == StatusPedido.CANCELADO) {
            btnAvancarStatus.setDisable(true);
        }
    }

    private VBox criarCardItem(ItemPedido itemPedido) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: #DC143C; -fx-padding: 10; -fx-background-radius: 5;");

        Label lblNome = new Label(itemPedido.getItem().getNome());
        lblNome.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold; -fx-font-size: 14;");

        Label lblQtd = new Label("Qtd: " + itemPedido.getQuantidade());
        lblQtd.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 12;");

        Label lblSubtotal = new Label(String.format("R$ %.2f", itemPedido.calcularSubtotal()));
        lblSubtotal.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold;");

        card.getChildren().addAll(lblNome, lblQtd, lblSubtotal);
        return card;
    }

    @FXML
    private void onAvancarStatus(ActionEvent event) {
        gerenciamentoController.avancarStatusPedido(pedido.getNumero());
        carregarDetalhes();
    }

    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-gerenciamento.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}