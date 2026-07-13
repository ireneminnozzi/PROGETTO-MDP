package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameUI extends Application {

    private static GameController game;

    public static void setGame(GameController controller) {
        game = controller;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("🌙 THE QUIET FOREST");

        MusicManager.getInstance().playLoop("musica_bosco.mp3");

        IntroUI introUI = new IntroUI(game);

        Scene scene = new Scene(introUI.getRoot(), 900, 600);
        stage.setScene(scene);
        stage.show();
    }
}
