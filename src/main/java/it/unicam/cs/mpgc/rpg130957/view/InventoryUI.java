package it.unicam.cs.mpgc.rpg130957.view;


import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        VBox root = new VBox(10);

        root.getChildren().add(new Label("🎒 Inventario"));

        game.getInventario().getTutti().forEach((item, qty) ->
                root.getChildren().add(new Label(item.getNome() + ": " + qty))
        );

        Button chiudi = new Button("Chiudi");
        chiudi.setOnAction(e -> stage.close());

        root.getChildren().add(chiudi);

        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
