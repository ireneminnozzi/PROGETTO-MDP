package it.unicam.cs.mpgc.rpg130957.persistence.json;

import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Serializza GameState in formato JSON.
 */
public class GameStateSerializer implements JsonSerializer<GameState> {

    @Override
    public JsonElement serialize(GameState state, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();

        obj.addProperty("salute", state.salute);
        obj.addProperty("mana", state.mana);
        obj.addProperty("livello", state.livello);
        obj.addProperty("esperienza", state.esperienza);

        JsonObject inv = new JsonObject();
        state.inventario.forEach(inv::addProperty);
        obj.add("inventario", inv);

        obj.addProperty("posizione", state.posizione);

        return obj;
    }
}
