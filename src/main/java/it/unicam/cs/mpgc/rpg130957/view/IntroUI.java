package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.awt.*;


public class IntroUI {

    private final VBox root = new VBox(20);

    public IntroUI(GameController game) {
        root.setAlignment(Pos.CENTER);

        Label titolo = new Label("🌙 La Strega di Lunargento");
        Label intro = new Label("Una giovane strega vive nella foresta...");

        Button start = new Button("Inizia");
        start.setOnAction(e -> {
            CapannaUI capanna = new CapannaUI(game);
            Scene scene = new Scene(capanna.getRoot(), 900, 600);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
        });

        root.getChildren().addAll(titolo, intro, start);
    }

    public VBox getRoot() {
        return root;
    }
}

