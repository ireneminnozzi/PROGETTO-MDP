package it.unicam.cs.mpgc.rpg130957.model.inventory;

import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;

import java.util.List;
import java.util.Map;


public class RecipeBook {

    public static final Recipe RICETTA_CURA = new Recipe(
            Map.of(
                    ItemRegistry.ERBA_MAGICA, 3,
                    ItemRegistry.MANDRAGORA, 1
            ),
            ItemRegistry.POZIONE_CURA
    );

    public static final Recipe RICETTA_MANA = new Recipe(
            Map.of(
                    ItemRegistry.ESSENZA_DRAGO, 2,
                    ItemRegistry.FUNGHI_SPETTRALI, 1
            ),
            ItemRegistry.POZIONE_MANA
    );

    public static final Recipe RICETTA_FORZA = new Recipe(
            Map.of(
                    ItemRegistry.ESSENZA_DRAGO, 2,
                    ItemRegistry.CRISTALLO_ARCANO, 1
            ),
            ItemRegistry.POZIONE_FORZA
    );

    public static final Recipe RICETTA_INVISIBILITA = new Recipe(
            Map.of(
                    ItemRegistry.CRISTALLO_ARCANO, 1,
                    ItemRegistry.FUNGHI_SPETTRALI, 2
            ),
            ItemRegistry.POZIONE_INVISIBILITA
    );

    public static final Recipe RICETTA_ELISIR_SUPREMO = new Recipe(
            Map.of(
                    ItemRegistry.CRISTALLO_ARCANO, 1,
                    ItemRegistry.ESSENZA_DRAGO, 2,
                    ItemRegistry.MANDRAGORA, 1
            ),
            ItemRegistry.ELISIR_SUPREMO
    );

    public static List<Recipe> getAllRecipes() {
        return List.of(
                RICETTA_CURA,
                RICETTA_MANA,
                RICETTA_FORZA,
                RICETTA_INVISIBILITA,
                RICETTA_ELISIR_SUPREMO
        );
    }
}
