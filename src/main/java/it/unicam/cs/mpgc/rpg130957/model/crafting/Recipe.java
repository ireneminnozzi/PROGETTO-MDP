package it.unicam.cs.mpgc.rpg130957.model.crafting;

import it.unicam.cs.mpgc.rpg130957.model.items.Ingredient;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;
import java.util.Map;

public class Recipe {

    private Map<Ingredient, Integer> ingredienti;
    private Potion risultato;

    public Recipe(Map<Ingredient, Integer> ingredienti, Potion risultato) {
        this.ingredienti = ingredienti;
        this.risultato = risultato;
    }

    public Map<Ingredient, Integer> getIngredienti() {
        return ingredienti;
    }

    public Potion getRisultato() {
        return risultato;
    }


    public String getNome() {
        return risultato.getNome() + " Recipe";
    }
}
