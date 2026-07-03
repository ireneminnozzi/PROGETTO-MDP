package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;

public class QuestManager {

    private QuestAvanzata questAttiva;

    public void assegnaQuest(QuestAvanzata quest) {
        this.questAttiva = quest;
        System.out.println("Nuova quest: " + quest.getNome());
    }

    public QuestAvanzata getQuestAttiva() {
        return questAttiva;
    }

    public void progressoRaccolta(Item item) {
        if (questAttiva == null) return;

        questAttiva.getObiettivi().stream()
                .filter(o -> o.getTipo() == QuestObjective.Tipo.RACCOGLI)
                .filter(o -> o.getItemRichiesto().equals(item))
                .forEach(QuestObjective::avanza);
    }

    public void progressoCombattimento(EnemyType tipo) {
        if (questAttiva == null) return;

        questAttiva.getObiettivi().stream()
                .filter(o -> o.getTipo() == QuestObjective.Tipo.SCONFIGGI)
                .filter(o -> o.getNemicoRichiesto() == tipo)
                .forEach(QuestObjective::avanza);
    }

    public boolean completaQuest(Inventario inventario, Wallet wallet) {
        if (questAttiva == null) return false;

        if (questAttiva.èCompletata()) {
            questAttiva.completa(wallet);
            questAttiva = null;
            return true;
        }

        return false;
    }
}
