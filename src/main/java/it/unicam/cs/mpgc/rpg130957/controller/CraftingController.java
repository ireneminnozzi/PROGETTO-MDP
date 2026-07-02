package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.Inventario.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Cauldron;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;

//Logica del crafting: controlla se puoi craftare, crafta tramite Cauldron

public class CraftingController {

    private final Inventario inventario;
    private final Cauldron cauldron;

    public CraftingController(Inventario inventario, Cauldron cauldron) {
        this.inventario = inventario;
        this.cauldron = cauldron;
    }

    public boolean puoiCraftare(Recipe recipe) {
        return recipe.getIngredienti().entrySet().stream()
                .allMatch(entry -> inventario.getQuantita(entry.getKey()) >= entry.getValue());
    }

    public Potion craft(Recipe recipe) {
        if (!puoiCraftare(recipe)) {
            return null;
        }
        return cauldron.craft(recipe, inventario);
    }
}
