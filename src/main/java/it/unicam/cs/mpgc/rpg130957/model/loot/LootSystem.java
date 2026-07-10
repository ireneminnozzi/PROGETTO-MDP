package it.unicam.cs.mpgc.rpg130957.model.loot;

import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;

import java.util.Random;

public class LootSystem {

    private static final Random rnd = new Random();

    public static Item dropCasuale() {

        int roll = rnd.nextInt(100);

        if (roll < 50) {
            return ItemRegistry.ERBA_MAGICA; // comune
        }
        if (roll < 75) {
            return ItemRegistry.FUNGHI_SPETTRALI; // non comune
        }
        if (roll < 90) {
            return ItemRegistry.MANDRAGORA; // raro
        }
        if (roll < 98) {
            return ItemRegistry.CRISTALLO_ARCANO; // epico
        }

        return ItemRegistry.ESSENZA_DRAGO; // leggendario
    }
}
