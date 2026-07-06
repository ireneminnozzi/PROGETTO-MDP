package it.unicam.cs.mpgc.rpg130957.persistence.json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GameStateDeserializer implements JsonDeserializer<GameState> {

    @Override
    public GameState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        GameState state = new GameState();

        state.salute = obj.get("salute").getAsInt();
        state.mana = obj.get("mana").getAsInt();
        state.livello = obj.get("livello").getAsInt();
        state.esperienza = obj.get("esperienza").getAsInt();

        JsonObject inv = obj.getAsJsonObject("inventario");
        Map<String, Integer> map = new HashMap<>();
        inv.entrySet().forEach(e -> map.put(e.getKey(), e.getValue().getAsInt()));
        state.inventario = map;

        state.posizione = obj.get("posizione").getAsString();

        return state;
    }
}
