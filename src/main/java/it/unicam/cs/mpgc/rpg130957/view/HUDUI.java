package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HUDUI {

    private final HBox root = new HBox(20);

    public HUDUI(GameController game) {
        root.getChildren().add(new Label("❤️ " + game.getPlayer().getSalute()));
        root.getChildren().add(new Label("🔮 " + game.getPlayer().getMana()));
        root.getChildren().add(new Label("⭐ Livello: " + game.getPlayer().getLivello()));
        root.getChildren().add(new Label("📘 XP: " + game.getPlayer().getEsperienza()));
    }

    public HBox getRoot() {
        return root;
    }
}
