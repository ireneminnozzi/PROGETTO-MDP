package it.unicam.cs.mpgc.rpg130957.persistence;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateDeserializer implements JsonDeserializer<GameState> {

    @Override
    public GameState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        GameState state = new GameState();

        // Player
        state.salute = obj.get("salute").getAsInt();
        state.mana = obj.get("mana").getAsInt();
        state.livello = obj.get("livello").getAsInt();
        state.esperienza = obj.get("esperienza").getAsInt();

        // Inventario
        JsonObject inv = obj.getAsJsonObject("inventario");
        Map<String, Integer> map = new HashMap<>();
        inv.entrySet().forEach(e -> map.put(e.getKey(), e.getValue().getAsInt()));
        state.inventario = map;

        // Posizione
        state.posizione = obj.get("posizione").getAsString();

        // Abilità
        state.abilitaSbloccate = context.deserialize(obj.get("abilitaSbloccate"), List.class);

        // Quest
        state.questAttiva = obj.get("questAttiva").getAsString();
        state.progressoQuest = context.deserialize(obj.get("progressoQuest"), Map.class);


        // Nemici
        state.nemiciPerArea = context.deserialize(obj.get("nemiciPerArea"), Map.class);

        // Magie
        state.magieSbloccate = context.deserialize(obj.get("magieSbloccate"), List.class);

        // Ricette
        state.ricetteSbloccate = context.deserialize(obj.get("ricetteSbloccate"), List.class);

        return state;
    }
}
