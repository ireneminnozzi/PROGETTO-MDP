package it.unicam.cs.mpgc.rpg130957.model.crafting;

import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Ingredient;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;


public class Cauldron {

    public Potion craft(Recipe recipe, Inventario inventario) {

        for (var entry : recipe.getIngredienti().entrySet()) {
            Ingredient ing = entry.getKey();
            int qta = entry.getValue();

            if (inventario.getQuantita(ing) < qta) {
                return null;
            }
        }

        for (var entry : recipe.getIngredienti().entrySet()) {
            inventario.rimuoviIngrediente(entry.getKey(), entry.getValue());
        }

        return recipe.getRisultato();
    }
}

