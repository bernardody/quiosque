module br.feevale.projetofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens br.feevale.projetofinal to javafx.fxml;
    opens br.feevale.projetofinal.view to javafx.fxml;

    exports br.feevale.projetofinal;
    exports br.feevale.projetofinal.view;
}