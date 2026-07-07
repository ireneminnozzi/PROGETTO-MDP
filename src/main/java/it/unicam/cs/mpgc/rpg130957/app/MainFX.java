package it.unicam.cs.mpgc.rpg130957.app;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.view.GameUI;
import javafx.application.Application;

public class MainFX {
    public static void main(String[] args) {
        GameController game = new GameController();
        GameUI.setGame(game);
        Application.launch(GameUI.class, args);
    }
}