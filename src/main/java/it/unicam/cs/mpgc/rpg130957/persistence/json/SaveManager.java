package it.unicam.cs.mpgc.rpg130957.persistence.json;

/**
 * Gestisce il salvataggio dello stato del gioco.
 */
public class SaveManager {

    public static void salva(GameState state) {
        GsonParser.write(state, "save.json");
        System.out.println("💾 Salvataggio completato!");
    }
}
