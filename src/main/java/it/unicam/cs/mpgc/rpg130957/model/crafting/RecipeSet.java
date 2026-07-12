package it.unicam.cs.mpgc.rpg130957.model.crafting;

import it.unicam.cs.mpgc.rpg130957.model.items.*;

import java.util.HashMap;
import java.util.Map;

public class RecipeSet {

    public static final Recipe POZIONE_CURA_RICETTA;
    public static final Recipe POZIONE_MANA_RICETTA;
    public static final Recipe POZIONE_FORZA_RICETTA;
    public static final Recipe POZIONE_INVISIBILITA_RICETTA;
    public static final Recipe ELISIR_SUPREMO_RICETTA;

    static {
// Pozione di Cura
        Map<Ingredient, Integer> curaIng = new HashMap<>();
        curaIng.put(ItemRegistry.MANDRAGORA, 2);
        curaIng.put(ItemRegistry.ERBA_MAGICA, 1);
        Potion pozioneCura = new Potion("Pozione di Cura", "Ripristina salute",  Rarity.UNCOMMON);
        POZIONE_CURA_RICETTA = new Recipe(curaIng, pozioneCura);

// Pozione di Mana
        Map<Ingredient, Integer> manaIng = new HashMap<>();
        manaIng.put(ItemRegistry.FUNGHI_SPETTRALI, 2);
        manaIng.put(ItemRegistry.ESSENZA_DRAGO, 1);
        Potion pozioneMana = new Potion("Pozione di Mana", "Rigenera energia magica" , Rarity.UNCOMMON);
        POZIONE_MANA_RICETTA = new Recipe(manaIng, pozioneMana);

// Pozione di Forza
        Map<Ingredient, Integer> forzaIng = new HashMap<>();
        forzaIng.put(ItemRegistry.ESSENZA_DRAGO, 2);
        forzaIng.put(ItemRegistry.CRISTALLO_ARCANO, 1);
        Potion pozioneForza = new Potion("Pozione di Forza", "Aumenta la forza fisica per breve tempo",  Rarity.RARE);
        POZIONE_FORZA_RICETTA = new Recipe(forzaIng, pozioneForza);

// Pozione di Invisibilità
        Map<Ingredient, Integer> invisIng = new HashMap<>();
        invisIng.put(ItemRegistry.CRISTALLO_ARCANO, 1);
        invisIng.put(ItemRegistry.FUNGHI_SPETTRALI, 2);
        Potion pozioneInvisibilita = new Potion("Pozione di Invisibilità", "Rende invisibili per pochi secondi",  Rarity.EPIC);
        POZIONE_INVISIBILITA_RICETTA = new Recipe(invisIng, pozioneInvisibilita);

// Elisir Supremo
        Map<Ingredient, Integer> elisirIng = new HashMap<>();
        elisirIng.put(ItemRegistry.CRISTALLO_ARCANO, 1);
        elisirIng.put(ItemRegistry.MANDRAGORA, 1);
        elisirIng.put(ItemRegistry.ESSENZA_DRAGO, 2);
        Potion elisirSupremo = new Potion("Elisir Supremo", "Potenzia tutte le statistiche",  Rarity.LEGENDARY);
        ELISIR_SUPREMO_RICETTA = new Recipe(elisirIng, elisirSupremo);
    }
}