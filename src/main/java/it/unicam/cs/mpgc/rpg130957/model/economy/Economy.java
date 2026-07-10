package it.unicam.cs.mpgc.rpg130957.model.economy;

import it.unicam.cs.mpgc.rpg130957.model.items.Rarity;

// classe non utilizzata, sta qui per implementazioni future
// un'implementazione futura potrebbe essere rivendere pozioni, erbe e armi

public class Economy {

    public static int prezzoBase(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 5;
            case UNCOMMON -> 15;
            case RARE -> 30;
            case EPIC -> 60;
            case LEGENDARY -> 120;
        };
    }

    public static int applicaSconto(int prezzo, double percentuale) {
        return (int) (prezzo * (1 - percentuale));
    }
}
