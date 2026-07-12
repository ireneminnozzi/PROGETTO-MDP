package it.unicam.cs.mpgc.rpg130957.persistence;

public class SaveManager {

    public static final String SAVE_FILE = "save.json";

    public static void salva(GameState state) {
        GsonParser.write(state, SAVE_FILE);
        System.out.println("💾 Salvataggio completato!");
    }
}