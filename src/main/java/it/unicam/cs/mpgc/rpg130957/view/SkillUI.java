package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.skills.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SkillUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        VBox root = new VBox(10);

        root.getChildren().add(new Label("✨ Abilità"));

        root.getChildren().add(new Label("Abilità sbloccate:"));
        game.getPlayer().getSkillTree().getAbilitàSbloccate().forEach(skill ->
                root.getChildren().add(new Label("- " + skill.getNome()))
        );

        root.getChildren().add(new Label("Sblocca abilità:"));

        for (Skill skill : SkillSet.getAll().values()) {
            Button sblocca = new Button(skill.getNome());
            sblocca.setOnAction(e -> game.getPlayer().getSkillTree().sblocca(skill));
            root.getChildren().add(sblocca);
        }


        Button chiudi = new Button("Chiudi");
        chiudi.setOnAction(e -> stage.close());

        root.getChildren().add(chiudi);

        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
