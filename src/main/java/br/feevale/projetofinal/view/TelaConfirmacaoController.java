package br.feevale.projetofinal.view;

import br.feevale.projetofinal.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaConfirmacaoController {
    
    @FXML private Label lblNumeroPedido;
    @FXML private Label lblFormaPagamento;
    @FXML private Button btnNovoPedido;
    @FXML private Button btnVoltar;
    
    private int numeroPedido;
    
    public void setInformacoes(String formaPagamento) {
        this.numeroPedido = MainApplication.getEstabelecimento().getTodosPedidos().size();
        
        lblNumeroPedido.setText("Número do Pedido: #" + String.format("%03d", numeroPedido));
        lblFormaPagamento.setText("Forma de Pagamento: " + formaPagamento);
    }
    
    @FXML
    private void onNovoPedido(ActionEvent event) {
        irParaTelaCardapio();
    }
    
    @FXML
    private void onVoltar(ActionEvent event) {
        irParaTelaInicial();
    }
    
    private void irParaTelaCardapio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-cardapio.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) btnNovoPedido.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void irParaTelaInicial() {
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