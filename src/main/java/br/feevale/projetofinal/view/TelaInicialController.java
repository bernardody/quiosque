package br.feevale.projetofinal.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button btnComecarPedido;

    @FXML
    private Button btnGerenciarPedidos;

    @FXML
    private void onComecarPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-cardapio.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnComecarPedido.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar tela de cardápio: " + e.getMessage());
        }
    }

    @FXML
    private void onGerenciarPedidos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/feevale/projetofinal/view/tela-gerenciamento.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnGerenciarPedidos.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar tela de gerenciamento: " + e.getMessage());
        }
    }
}