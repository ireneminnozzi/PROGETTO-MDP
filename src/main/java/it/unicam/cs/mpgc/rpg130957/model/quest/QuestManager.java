package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;

//Gestisce la quest attiva, verifica il completamento e assegna ricompense.

public class QuestManager {

    private Quest questAttiva;

    public void assegnaQuest(Quest quest) {
        this.questAttiva = quest;
    }

    public boolean completaQuest(Inventario inventario, Wallet wallet) {
        if (questAttiva == null) return false;

        if (inventario.getQuantita(questAttiva.getOggettoRichiesto()) >= questAttiva.getQuantitaRichiesta()) {
            inventario.rimuoviIngrediente(questAttiva.getOggettoRichiesto(), questAttiva.getQuantitaRichiesta());
            wallet.earn(questAttiva.getRicompensaOro());
            questAttiva = null;
            return true;
        }

        return false;
    }

    public Quest getQuestAttiva() {
        return questAttiva;
    }
}
