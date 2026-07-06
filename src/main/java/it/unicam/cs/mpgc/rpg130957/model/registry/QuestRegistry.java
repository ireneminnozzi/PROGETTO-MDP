package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.quest.QuestAvanzata;
import java.util.HashMap;
import java.util.Map;

public class QuestRegistry {

    private static final Map<String, QuestAvanzata> quests = new HashMap<>();

    public static void register(QuestAvanzata quest) {
        quests.put(quest.getNome(), quest);
    }

    public static QuestAvanzata getByName(String nome) {
        return quests.get(nome);
    }

    public static Map<String, QuestAvanzata> getAll() {
        return quests;
    }
}
