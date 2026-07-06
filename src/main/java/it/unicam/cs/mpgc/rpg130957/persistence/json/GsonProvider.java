package it.unicam.cs.mpgc.rpg130957.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Provider centralizzato per ottenere l'istanza di Gson configurata.
 */
public class GsonProvider {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(GameState.class, new GameStateSerializer())
            .registerTypeAdapter(GameState.class, new GameStateDeserializer())
            .setPrettyPrinting()
            .create();

    private GsonProvider() {} // evita istanziazione

    public static Gson getGson() {
        return gson;
    }
}
