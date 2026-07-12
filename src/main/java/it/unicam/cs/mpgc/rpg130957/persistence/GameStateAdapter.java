package it.unicam.cs.mpgc.rpg130957.persistence;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Un solo adapter per serializzazione E deserializzazione di GameState.
 *
 * Prima c'erano due classi separate (GameStateSerializer e GameStateDeserializer)
 * entrambe registrate su GameState.class in GsonProvider: Gson però tiene un solo
 * adapter per tipo, quindi in scrittura il serializer veniva ignorato in silenzio
 * e si usava la reflection di default (funzionava "per caso" solo perché i nomi
 * dei campi coincidevano).
 *
 * In più, progressoQuest e nemiciPerArea ora vengono letti a mano (come già
 * succedeva per inventario) invece di passare "Map.class" grezzo a Gson: senza
 * i generics, Gson deserializza i numeri come Double, e un cast a Integer
 * lanciava ClassCastException a runtime.
 */
public class GameStateAdapter implements JsonSerializer<GameState>, JsonDeserializer<GameState> {

    @Override
    public JsonElement serialize(GameState state, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        // Player
        obj.addProperty("salute", state.salute);
        obj.addProperty("mana", state.mana);
        obj.addProperty("livello", state.livello);
        obj.addProperty("esperienza", state.esperienza);
        obj.addProperty("posizione", state.posizione);

        // Inventario
        JsonObject inv = new JsonObject();
        state.inventario.forEach(inv::addProperty);
        obj.add("inventario", inv);

        // Abilità
        obj.add("abilitaSbloccate", stringListToJson(state.abilitaSbloccate));

        // Quest
        obj.addProperty("questAttiva", state.questAttiva);
        JsonObject quest = new JsonObject();
        state.progressoQuest.forEach(quest::addProperty);
        obj.add("progressoQuest", quest);

        // Boss
        obj.addProperty("bossSconfitto", state.bossSconfitto);

        // Nemici
        JsonObject nemici = new JsonObject();
        state.nemiciPerArea.forEach(nemici::addProperty);
        obj.add("nemiciPerArea", nemici);

        // Magie
        obj.add("magieSbloccate", stringListToJson(state.magieSbloccate));

        // Ricette
        obj.add("ricetteSbloccate", stringListToJson(state.ricetteSbloccate));

        return obj;
    }

    @Override
    public GameState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        GameState state = new GameState();

        // Player
        state.salute = getInt(obj, "salute", 0);
        state.mana = getInt(obj, "mana", 0);
        state.livello = getInt(obj, "livello", 1);
        state.esperienza = getInt(obj, "esperienza", 0);
        state.posizione = getString(obj, "posizione", null);

        // Inventario
        state.inventario = readIntMap(obj, "inventario");

        // Abilità
        state.abilitaSbloccate = readStringList(obj, "abilitaSbloccate");

        // Quest
        state.questAttiva = getString(obj, "questAttiva", null);
        state.progressoQuest = readIntMap(obj, "progressoQuest");

        // Nemici
        state.nemiciPerArea = readIntMap(obj, "nemiciPerArea");

        // Magie
        state.magieSbloccate = readStringList(obj, "magieSbloccate");

        // Ricette
        state.ricetteSbloccate = readStringList(obj, "ricetteSbloccate");

        return state;
    }



    private int getInt(JsonObject obj, String key, int fallback) {
        return (obj.has(key) && !obj.get(key).isJsonNull()) ? obj.get(key).getAsInt() : fallback;
    }

    private String getString(JsonObject obj, String key, String fallback) {
        return (obj.has(key) && !obj.get(key).isJsonNull()) ? obj.get(key).getAsString() : fallback;
    }

    private Map<String, Integer> readIntMap(JsonObject obj, String key) {
        Map<String, Integer> map = new HashMap<>();
        if (!obj.has(key) || obj.get(key).isJsonNull()) return map;
        obj.getAsJsonObject(key).entrySet()
                .forEach(e -> map.put(e.getKey(), e.getValue().getAsInt()));
        return map;
    }

    private List<String> readStringList(JsonObject obj, String key) {
        List<String> list = new ArrayList<>();
        if (!obj.has(key) || obj.get(key).isJsonNull()) return list;
        obj.getAsJsonArray(key).forEach(e -> list.add(e.getAsString()));
        return list;
    }

    // ---- scrittura ----

    private JsonArray stringListToJson(List<String> list) {
        JsonArray arr = new JsonArray();
        if (list != null) list.forEach(arr::add);
        return arr;
    }
}