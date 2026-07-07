package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.quest.QuestAvanzata;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        VBox root = new VBox(10);

        root.getChildren().add(new Label("📜 Quest"));

        QuestAvanzata q = game.getQuestManager().getQuestAttiva();

        if (q == null) {
            root.getChildren().add(new Label("Nessuna quest attiva."));
        } else {
            root.getChildren().add(new Label("Quest: " + q.getNome()));

            q.getObiettivi().forEach(o ->
                    root.getChildren().add(new Label(
                            o.getTipo() + ": " + o.getProgresso() + "/" + o.getQuantitaRichiesta()
                    ))
            );

            if (q.èCompletata()) {
                Button completa = new Button("Completa quest");
                completa.setOnAction(e -> {
                    game.completaQuest();
                    stage.close();
                });
                root.getChildren().add(completa);
            }
        }

        Button chiudi = new Button("Chiudi");
        chiudi.setOnAction(e -> stage.close());

        root.getChildren().add(chiudi);

        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
