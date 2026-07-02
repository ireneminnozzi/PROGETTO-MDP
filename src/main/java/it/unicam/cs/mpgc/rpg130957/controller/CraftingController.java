package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.crafting.Cauldron;
import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;

//Logica del crafting: controlla se puoi craftare, crafta tramite Cauldron

public class CraftingController {

    private final Inventario inventario;
    private final Cauldron cauldron;
    private final Player player;

    public CraftingController(Inventario inventario, Cauldron cauldron, Player player) {
        this.inventario = inventario;
        this.cauldron = cauldron;
        this.player = player;
    }

    public boolean puoiCraftare(Recipe recipe) {
        return recipe.getIngredienti().entrySet().stream()
                .allMatch(entry -> inventario.getQuantita(entry.getKey()) >= entry.getValue());
    }

    public Potion craft(Recipe recipe) {
        Potion p = cauldron.craft(recipe, inventario);
        if (p != null) {
            player.usaPozione(p);
        }
        return p;
    }

}
