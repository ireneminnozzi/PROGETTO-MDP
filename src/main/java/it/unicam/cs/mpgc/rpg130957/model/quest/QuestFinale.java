package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import java.util.List;

public class QuestFinale {

    public static QuestAvanzata creaQuestFinale() {
        return new QuestAvanzata(
                "Il Risveglio del Drago Primordiale",
                List.of(
                        QuestObjective.raccogli(ItemRegistry.CRISTALLO_ARCANO, 1),
                        QuestObjective.raccogli(ItemRegistry.POLVERE_STELLARE, 2),
                        QuestObjective.sconfiggi(it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType.SPIRITO, 3)
                ),
                150
        );
    }
}
