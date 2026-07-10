package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.forest.HerbGuardians;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.loot.EnemyLootTable;
import it.unicam.cs.mpgc.rpg130957.model.loot.LootSystem;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;
import it.unicam.cs.mpgc.rpg130957.model.quest.QuestManager;

public class ForestController {

    private ForestArea posizione;
    private final Inventario inventario;
    private final Player player;
    private final QuestManager questManager;

    public ForestController(ForestArea start, Inventario inventario, Player player, QuestManager questManager) {
        this.posizione = start;
        this.inventario = inventario;
        this.player = player;
        this.questManager = questManager;
    }

    public ForestArea getPosizione() {
        return posizione;
    }

    public void setPosizione(ForestArea posizione) {
        this.posizione = posizione;
        this.posizione.rigeneraSeVuota();
    }

    public boolean muovi(ForestArea destinazione) {
        if (posizione.getCollegamenti().contains(destinazione)) {
            posizione = destinazione;
            posizione.rigeneraSeVuota();
            return true;
        }
        return false;
    }

    public boolean raccogliRisorsa() {

        if (posizione.getRisorse().isEmpty()) return false;

        Item erba = posizione.getRisorse().get(0);

        EnemyType guardiano = it.unicam.cs.mpgc.rpg130957.model.forest.HerbGuardians.getGuardiano(erba);

        if (guardiano != null) {
            Enemy nemico = posizione.getNemici().stream()
                    .filter(e -> e.getTipo() == guardiano)
                    .findFirst()
                    .orElse(null);

            if (nemico != null) {
                boolean vinto = CombatSystem.combatti(nemico, it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry.BASTONE_MAGICO, player);
                if (!vinto) return false;
                posizione.getNemici().remove(nemico);
                questManager.progressoCombattimento(nemico.getTipo());
            }
        }

        posizione.getRisorse().remove(erba);
        inventario.aggiungiIngrediente(erba, 1);
        questManager.progressoRaccolta(erba);

        return true;
    }

    public Item getErbaDisponibile() {
        if (posizione.getRisorse().isEmpty()) return null;
        return posizione.getRisorse().get(0);
    }

    public Enemy trovaGuardiano(Item erba) {
        EnemyType guardiano = HerbGuardians.getGuardiano(erba);
        if (guardiano == null) return null;

        return posizione.getNemici().stream()
                .filter(e -> e.getTipo() == guardiano)
                .findFirst()
                .orElse(null);
    }

    public boolean raccogliErbaLibera() {
        Item erba = getErbaDisponibile();
        if (erba == null) return false;
        if (trovaGuardiano(erba) != null) return false;

        posizione.getRisorse().remove(erba);
        inventario.aggiungiIngrediente(erba, 1);
        questManager.progressoRaccolta(erba);
        return true;
    }

    public void completaRaccoltaDopoVittoria(Item erba, Enemy nemico) {
        posizione.getNemici().remove(nemico);
        questManager.progressoCombattimento(nemico.getTipo());

        if (posizione.getRisorse().contains(erba)) {
            posizione.getRisorse().remove(erba);
            inventario.aggiungiIngrediente(erba, 1);
            questManager.progressoRaccolta(erba);
        }
    }

    public void finalizzaCombattimento(Enemy nemico) {
        posizione.getNemici().remove(nemico);
        ottieniLoot(nemico);
        player.guadagnaXP(20);
        questManager.progressoCombattimento(nemico.getTipo());
    }

    public boolean combattiNemico(Weapon arma) {
        if (posizione.getNemici().isEmpty()) return false;

        Enemy enemy = posizione.getNemici().get(0);

        boolean vinto = CombatSystem.combatti(enemy, arma, player);

        if (vinto) {
            posizione.getNemici().remove(enemy);
            ottieniLoot(enemy);
            player.guadagnaXP(20);
            questManager.progressoCombattimento(enemy.getTipo());
        }

        return vinto;
    }

    private void ottieniLoot(Enemy enemy) {
        Item dropFisso = EnemyLootTable.getLoot(enemy.getTipo());
        Item dropBonus = LootSystem.dropCasuale();

        inventario.aggiungiIngrediente(dropFisso, 1);

        if (dropBonus != null)
            inventario.aggiungiIngrediente(dropBonus, 1);
    }
}
