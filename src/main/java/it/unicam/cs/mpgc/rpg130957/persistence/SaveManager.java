package it.unicam.cs.mpgc.rpg130957.persistence;

public class SaveManager {

    public static void salva(GameState state) {
        GsonParser.write(state, "save.json");
        System.out.println("💾 Salvataggio completato!");
    }
}
