package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CombatUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        VBox root = new VBox(10);

        root.getChildren().add(new Label("⚔️ Combattimento!"));

        if (game.getPosizione().getNemici().isEmpty()) {
            root.getChildren().add(new Label("Nessun nemico presente."));
        } else {
            Enemy nemico = game.getPosizione().getNemici().get(0);
            root.getChildren().add(new Label("Nemico: " + nemico.getNome()));

            Button combatti = new Button("Combatti");
            combatti.setOnAction(e -> {
                Weapon arma = it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry.BASTONE_MAGICO;
                boolean vinto = game.combatti(arma);
                System.out.println(vinto ? "Hai vinto il combattimento!" : "Hai perso...");
                stage.close();
            });

            root.getChildren().add(combatti);
        }

        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
