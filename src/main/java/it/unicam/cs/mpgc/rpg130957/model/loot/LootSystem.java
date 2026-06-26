package it.unicam.cs.mpgc.rpg130957.model.loot;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;

//Genera item casuali basati su probabilità.

public class LootSystem {

    public static Item dropCasuale() {
        double r = Math.random();

        if (r < 0.50) return ItemRegistry.ERBA_MAGICA;
        if (r < 0.75) return ItemRegistry.MANDRAGORA;
        if (r < 0.90) return ItemRegistry.FIORE_LUNARE;
        if (r < 0.98) return ItemRegistry.CRISTALLO_ARCANO;
        return ItemRegistry.ESSENZA_DRAGO;
    }

}

