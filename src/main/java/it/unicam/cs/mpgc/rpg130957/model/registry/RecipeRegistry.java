package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {

    private static final Map<String, Recipe> recipes = new HashMap<>();

    public static void register(Recipe recipe) {
        recipes.put(recipe.getNome(), recipe);
    }

    public static Recipe getByName(String nome) {
        return recipes.get(nome);
    }

    public static Map<String, Recipe> getAll() {
        return recipes;
    }
}
