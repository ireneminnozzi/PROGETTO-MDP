package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CapannaUI {

    private final VBox root = new VBox(20);

    public CapannaUI(GameController game) {
        root.setAlignment(Pos.CENTER);

        Label descrizione = new Label("Ti trovi nella tua capanna...");

        Button pozioni = new Button("Banco pozioni");
        Button esci = new Button("Esci dalla capanna");

        esci.setOnAction(e -> {
            MapUI map = new MapUI(game);
            Scene scene = new Scene(map.getRoot(), 900, 600);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
        });

        root.getChildren().addAll(descrizione, pozioni, esci);
    }

    public VBox getRoot() {
        return root;
    }
}
