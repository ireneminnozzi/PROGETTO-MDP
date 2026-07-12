package it.unicam.cs.mpgc.rpg130957.model.items;

import java.util.List;



public class ItemRegistry {

    // INGREDIENTI
    public static final Ingredient ERBA_MAGICA =
            new Ingredient("Erba Magica", "Una pianta luminosa che cresce vicino ai ruscelli.", Rarity.COMMON);

    public static final Ingredient MANDRAGORA =
            new Ingredient("Radice di Mandragora", "Una radice urlante dalle proprietà mistiche.", Rarity.UNCOMMON);

    public static final Ingredient FIORE_LUNARE =
           new Ingredient("Fiore Lunare", "Sboccia solo nelle notti di luna piena.",  Rarity.RARE);

    public static final Ingredient CRISTALLO_ARCANO =
            new Ingredient("Cristallo Arcano", "Un cristallo pulsante di energia magica.",  Rarity.EPIC);

    public static final Ingredient ESSENZA_DRAGO =
            new Ingredient("Essenza del Drago", "Un frammento di potere antico e pericoloso.",  Rarity.LEGENDARY);

    public static final Ingredient FUNGHI_SPETTRALI =
            new Ingredient("Funghi Spettrali", "Crescono nelle caverne infestate.",  Rarity.UNCOMMON);
   public static final Ingredient LACRIMA_FENICE =
            new Ingredient("Lacrima di Fenice", "Una goccia di pura rinascita.",  Rarity.EPIC);

    // POZIONI
    public static final Potion POZIONE_CURA =
            new Potion("Pozione di Cura", "Ripristina una piccola quantità di salute.",  Rarity.UNCOMMON);

    public static final Potion POZIONE_MANA =
            new Potion("Pozione di Mana", "Rigenera energia magica.",  Rarity.UNCOMMON);

    public static final Potion POZIONE_FORZA =
            new Potion("Pozione di Forza", "Aumenta la forza fisica per breve tempo.",  Rarity.RARE);

    public static final Potion POZIONE_INVISIBILITA =
            new Potion("Pozione di Invisibilità", "Rende invisibili per pochi secondi.",  Rarity.EPIC);

    public static final Potion ELISIR_SUPREMO =
            new Potion("Elisir Supremo", "Potenzia tutte le statistiche.",  Rarity.LEGENDARY);

    //ARMI
    public static final Weapon BASTONE_MAGICO =
            new Weapon("Bastone Magico", "Un semplice bastone incantato.",  Rarity.UNCOMMON, 10);



    public static List<Item> getAllItems() {
        return List.of(
                ERBA_MAGICA, MANDRAGORA, FIORE_LUNARE, CRISTALLO_ARCANO, ESSENZA_DRAGO,
                FUNGHI_SPETTRALI, LACRIMA_FENICE,
                POZIONE_CURA, POZIONE_MANA, POZIONE_FORZA, POZIONE_INVISIBILITA, ELISIR_SUPREMO
        );
    }
}
