package it.unicam.cs.mpgc.rpg130957.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(GameState.class, new GameStateSerializer())
            .registerTypeAdapter(GameState.class, new GameStateDeserializer())
            .setPrettyPrinting()
            .create();

    public static Gson getGson() {
        return gson;
    }
}
