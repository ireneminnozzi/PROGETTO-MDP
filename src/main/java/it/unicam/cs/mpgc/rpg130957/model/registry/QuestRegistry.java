package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.quest.QuestAvanzata;
import java.util.HashMap;
import java.util.Map;

public class QuestRegistry {

    private static final Map<String, QuestAvanzata> quests = new HashMap<>();

    public static QuestAvanzata getByName(String nome) {
        return quests.get(nome);
    }

}
