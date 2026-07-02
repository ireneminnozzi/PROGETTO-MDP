package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.loot.EnemyLootTable;
import it.unicam.cs.mpgc.rpg130957.model.loot.LootSystem;

//ForestController gestisce l’esperienza della strega nel bosco.

public class ForestController {

    private ForestArea posizione;
    private Inventario inventario;   //  AGGIUNTO

    public ForestController(ForestArea start, Inventario inventario) {
        this.posizione = start;
        this.inventario = inventario;   // ️ SALVATO
    }

    public ForestArea getPosizione() {
        return posizione;
    }

    public boolean muovi(ForestArea destinazione) {
        if (posizione.getCollegamenti().contains(destinazione)) {
            posizione = destinazione;
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
                boolean vinto = CombatSystem.combatti(nemico, it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry.BASTONE_MAGICO);
                if (!vinto) return false;
                posizione.getNemici().remove(nemico);
            }
        }

        posizione.getRisorse().remove(erba);
        inventario.aggiungiIngrediente(erba, 1);

        return true;
    }


    public boolean combattiNemico(Weapon arma) {
        if (posizione.getNemici().isEmpty()) return false;

        Enemy enemy = posizione.getNemici().get(0);

        boolean vinto = CombatSystem.combatti(enemy, arma);

        if (vinto) {
            posizione.getNemici().remove(enemy);
            ottieniLoot(enemy);   // ✔️ LOOT DOPO LA VITTORIA
        }

        return vinto;
    }

    private void ottieniLoot(Enemy enemy) {
        Item dropFisso = EnemyLootTable.getLoot(enemy.getTipo());
        Item dropBonus = LootSystem.dropCasuale(); // o dropBonus()

        inventario.aggiungiIngrediente(dropFisso, 1);

        if (dropBonus != null)
            inventario.aggiungiIngrediente(dropBonus, 1);
    }
}
