package it.unicam.cs.mpgc.rpg130957.persistence;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadManager {

    public static GameState carica() {
        try {
            if (!Files.exists(Paths.get(SaveManager.SAVE_FILE))) {
                System.out.println("Nessun salvataggio trovato.");
                return null;
            }
            return GsonParser.read(SaveManager.SAVE_FILE);
        } catch (Exception e) {
            System.out.println("Errore nel caricamento: " + e.getMessage());
            return null;
        }
    }
}