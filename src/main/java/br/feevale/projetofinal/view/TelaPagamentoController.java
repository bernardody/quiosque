package br.feevale.projetofinal.view;

import br.feevale.projetofinal.controller.PedidoController;
import br.feevale.projetofinal.model.pagamento.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaPagamentoController {
    
    @FXML private Label lblTotal;
    @FXML private Button btnDinheiro;
    @FXML private Button btnCartaoDebito;
    @FXML private Button btnCartaoCredito;
    @FXML private Button btnPix;
    @FXML private Button btnVoltar;
    
    private PedidoController pedidoController;
    
    public void setPedidoController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
        lblTotal.setText(String.format("Total: R$ %.2f", pedidoController.getValorTotal()));
    }
    
    @FXML
    private void onPagarDinheiro(ActionEvent event) {
        processarPagamento(new PagamentoDinheiro());
    }
    
    @FXML
    private void onPagarCartaoDebito(ActionEvent event) {
        processarPagamento(new PagamentoCartao("Débito"));
    }
    
    @FXML
    private void onPagarCartaoCredito(ActionEvent event) {
        processarPagamento(new PagamentoCartao("Crédito"));
    }
    
    @FXML
    private void onPagarPix(ActionEvent event) {
        processarPagamento(new PagamentoPix());
    }
    
    private void processarPagamento(FormaPagamento formaPagamento) {
        if (pedidoController.finalizarPedido(formaPagamento)) {
            irParaTelaConfirmacao(formaPagamento);
        }
    }
    
    private void irParaTelaConfirmacao(FormaPagamento formaPagamento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-confirmacao.fxml"));
            Parent root = loader.load();
            
            TelaConfirmacaoController controller = loader.getController();
            controller.setInformacoes(formaPagamento.getTipo());
            
            Stage stage = (Stage) btnDinheiro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-resumo.fxml"));
            Parent root = loader.load();
            
            TelaResumoController controller = loader.getController();
            controller.setPedidoController(pedidoController);
            
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}