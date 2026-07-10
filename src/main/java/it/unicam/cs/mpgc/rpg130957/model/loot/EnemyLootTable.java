package it.unicam.cs.mpgc.rpg130957.model.loot;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;



public class EnemyLootTable {

    public static Item getLoot(EnemyType tipo) {

        return switch (tipo) {
            case LUPO -> ItemRegistry.ERBA_MAGICA;
            case GOBLIN -> ItemRegistry.MANDRAGORA;
            case SPIRITO -> ItemRegistry.FIORE_LUNARE;
            case SCHELETRO -> ItemRegistry.CRISTALLO_ARCANO;
            case DRAGO -> ItemRegistry.ESSENZA_DRAGO;
        };
    }
}
