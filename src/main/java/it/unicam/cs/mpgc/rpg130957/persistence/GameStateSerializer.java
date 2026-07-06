package it.unicam.cs.mpgc.rpg130957.persistence;

import com.google.gson.*;
import java.lang.reflect.Type;

public class GameStateSerializer implements JsonSerializer<GameState> {

    @Override
    public JsonElement serialize(GameState state, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();

        // Player
        obj.addProperty("salute", state.salute);
        obj.addProperty("mana", state.mana);
        obj.addProperty("livello", state.livello);
        obj.addProperty("esperienza", state.esperienza);

        // Inventario
        JsonObject inv = new JsonObject();
        state.inventario.forEach(inv::addProperty);
        obj.add("inventario", inv);

        // Posizione
        obj.addProperty("posizione", state.posizione);

        // Abilità
        obj.add("abilitaSbloccate", context.serialize(state.abilitaSbloccate));

        // Quest
        obj.addProperty("questAttiva", state.questAttiva);
        obj.add("progressoQuest", context.serialize(state.progressoQuest));

        // Boss
        obj.addProperty("bossSconfitto", state.bossSconfitto);

        // Nemici
        obj.add("nemiciPerArea", context.serialize(state.nemiciPerArea));

        // Magie
        obj.add("magieSbloccate", context.serialize(state.magieSbloccate));

        // Ricette
        obj.add("ricetteSbloccate", context.serialize(state.ricetteSbloccate));

        return obj;
    }
}
