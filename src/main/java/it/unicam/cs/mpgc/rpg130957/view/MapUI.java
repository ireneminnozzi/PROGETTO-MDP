package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MapUI {

    private final VBox root = new VBox(10);
    private final GameController game;

    public MapUI(GameController game) {
        this.game = game;
        aggiorna();
    }

    public void aggiorna() {
        root.getChildren().clear();

        ForestArea area = game.getPosizione();

        root.getChildren().add(new Label("Area: " + area.getNome()));
        root.getChildren().add(new Label("Risorse: " + area.getRisorse()));
        root.getChildren().add(new Label("Nemici: " + area.getNemici()));

        Button raccogli = new Button("Raccogli Risorsa");
        raccogli.setOnAction(e -> {
            game.raccogli();
            aggiorna();
        });

        Button combatti = new Button("Combatti");
        combatti.setOnAction(e -> CombatUI.show(game));

        root.getChildren().add(raccogli);
        root.getChildren().add(combatti);

        for (ForestArea collegata : area.getCollegamenti()) {
            Button move = new Button("Vai a " + collegata.getNome());
            move.setOnAction(e -> {
                game.muovi(collegata);
                aggiorna();
            });
            root.getChildren().add(move);
        }
    }

    public VBox getRoot() {
        return root;
    }
}
