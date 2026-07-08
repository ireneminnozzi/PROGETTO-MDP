package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class IntroUI {

    private final VBox root = new VBox(18);

    public IntroUI(GameController game) {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #1b1330;");

        Label titolo = new Label("🌙 THE QUIET FOREST");
        titolo.setFont(Font.font(28));
        titolo.setStyle("-fx-text-fill: #e6d9ff;");

        Label intro = new Label(
                "Sei una giovane strega che vive in una capanna ai margini del bosco.\n\n" +
                        "Nella tua capanna puoi preparare pozioni al banco degli intrugli, " +
                        "usando gli ingredienti che raccogli nella foresta.\n\n" +
                        "Ogni erba magica del bosco è custodita da un mostro: quando la raccogli, " +
                        "il guardiano ti sfida e dovrai affrontarlo in un combattimento a turni, " +
                        "scegliendo di volta in volta se attaccare, lanciare una magia, " +
                        "bere una pozione o fuggire.\n\n" +
                        "Esplora la mappa, raccogli ingredienti, sconfiggi i mostri e diventa " +
                        "un'alchimista sempre più potente!"
        );
        intro.setWrapText(true);
        intro.setMaxWidth(640);
        intro.setTextAlignment(TextAlignment.CENTER);
        intro.setStyle("-fx-text-fill: #cdbfe8; -fx-font-size: 14px;");

        Button start = new Button("Continua");
        start.setOnAction(e -> {
            MenuUI menu = new MenuUI(game);
            Scene scene = new Scene(menu.getRoot(), 900, 600);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
        });

        root.getChildren().addAll(titolo, intro, start);
    }

    public VBox getRoot() {
        return root;
    }
}
