package it.unicam.cs.mpgc.rpg130957.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

/**
 * Gestisce la scrittura e lettura del file JSON.
 */
public class GsonParser {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(GameState.class, new GameStateSerializer())
            .registerTypeAdapter(GameState.class, new GameStateDeserializer())
            .setPrettyPrinting()
            .create();

    public static void write(GameState state, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(state, writer);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura: " + e.getMessage());
        }
    }

    public static GameState read(String file) {
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, GameState.class);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
        }
        return null;
    }
}
