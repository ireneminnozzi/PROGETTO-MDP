package it.unicam.cs.mpgc.rpg130957.model.items;

import java.util.List;



public class ItemRegistry {

    // INGREDIENTI
    public static final Ingredient ERBA_MAGICA =
            new Ingredient("Erba Magica", "Una pianta luminosa che cresce vicino ai ruscelli.", 5, Rarity.COMMON);

    public static final Ingredient MANDRAGORA =
            new Ingredient("Radice di Mandragora", "Una radice urlante dalle proprietà mistiche.", 12, Rarity.UNCOMMON);

    public static final Ingredient FIORE_LUNARE =
           new Ingredient("Fiore Lunare", "Sboccia solo nelle notti di luna piena.", 25, Rarity.RARE);

    public static final Ingredient CRISTALLO_ARCANO =
            new Ingredient("Cristallo Arcano", "Un cristallo pulsante di energia magica.", 50, Rarity.EPIC);

    public static final Ingredient ESSENZA_DRAGO =
            new Ingredient("Essenza del Drago", "Un frammento di potere antico e pericoloso.", 120, Rarity.LEGENDARY);

    public static final Ingredient FUNGHI_SPETTRALI =
            new Ingredient("Funghi Spettrali", "Crescono nelle caverne infestate.", 18, Rarity.UNCOMMON);

//    public static final Ingredient POLVERE_STELLARE =
//            new Ingredient("Polvere Stellare", "Residuo di meteoriti magici.", 40, Rarity.RARE);
//
    public static final Ingredient LACRIMA_FENICE =
            new Ingredient("Lacrima di Fenice", "Una goccia di pura rinascita.", 90, Rarity.EPIC);
//
//    public static final Ingredient CORNO_UNICORNO =
//            new Ingredient("Corno di Unicorno", "Raro e potentissimo, simbolo di purezza.", 150, Rarity.LEGENDARY);
//
//    public static final Ingredient PETALO_ROSA_NERA =
//            new Ingredient("Petalo di Rosa Nera", "Un fiore maledetto che cresce nel buio.", 30, Rarity.RARE);

    // POZIONI
    public static final Potion POZIONE_CURA =
            new Potion("Pozione di Cura", "Ripristina una piccola quantità di salute.", 20, Rarity.UNCOMMON);

    public static final Potion POZIONE_MANA =
            new Potion("Pozione di Mana", "Rigenera energia magica.", 22, Rarity.UNCOMMON);

    public static final Potion POZIONE_FORZA =
            new Potion("Pozione di Forza", "Aumenta la forza fisica per breve tempo.", 35, Rarity.RARE);

    public static final Potion POZIONE_INVISIBILITA =
            new Potion("Pozione di Invisibilità", "Rende invisibili per pochi secondi.", 60, Rarity.EPIC);

    public static final Potion ELISIR_SUPREMO =
            new Potion("Elisir Supremo", "Potenzia tutte le statistiche.", 200, Rarity.LEGENDARY);

    //ARMI
    public static final Weapon BASTONE_MAGICO =
            new Weapon("Bastone Magico", "Un semplice bastone incantato.", 30, Rarity.UNCOMMON, 10);

    // armi messe per implementazioni future
    public static final Weapon PUGNALE_OSCURO =
            new Weapon("Pugnale Oscuro", "Un'arma rapida e letale.", 50, Rarity.RARE, 18);

    public static final Weapon SPADA_DRAGO =
            new Weapon("Spada del Drago", "Forgiata con essenza draconica.", 150, Rarity.EPIC, 35);


    public static List<Item> getAllItems() {
        return List.of(
                ERBA_MAGICA, MANDRAGORA, FIORE_LUNARE, CRISTALLO_ARCANO, ESSENZA_DRAGO,
                FUNGHI_SPETTRALI, LACRIMA_FENICE,
                POZIONE_CURA, POZIONE_MANA, POZIONE_FORZA, POZIONE_INVISIBILITA, ELISIR_SUPREMO
        );
    }
}
