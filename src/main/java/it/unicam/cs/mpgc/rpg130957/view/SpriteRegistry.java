package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Carica e mette in cache gli sprite reali del gioco da src/main/resources/sprites.
 * Se uno sprite non è ancora disponibile, i metodi restituiscono null e le view
 * ricadono su una rappresentazione grafica di riserva (forme/colori).
 */
public final class SpriteRegistry {

    private static final Map<String, Image> CACHE = new HashMap<>();

    private SpriteRegistry() {
    }

    public static Image getWitchFront() {
        return carica("witch_front.png");
    }

    public static Image getWitchBack() {
        return carica("witch_back.png");
    }

    public static Image getMappaBosco() {
        return carica("map_bosco.png");
    }

    public static Image getEnemyImage(EnemyType tipo) {
        if (tipo == null) return null;
        String file = switch (tipo) {
            case LUPO -> "enemy_lupo.png";
            case GOBLIN -> "enemy_goblin.png";
            case SPIRITO -> "enemy_spirito.png";
            case SCHELETRO -> "enemy_scheletro.png";
            case DRAGO -> "enemy_drago.png";
        };
        return carica(file);
    }

    public static Image getItemImage(Item item) {
        if (item == null) return null;

        if (item == ItemRegistry.ERBA_MAGICA) return carica("herb_erba_magica.png");
        if (item == ItemRegistry.FIORE_LUNARE) return carica("herb_fiore_lunare.png");
        if (item == ItemRegistry.MANDRAGORA) return carica("herb_mandragora.png");
        if (item == ItemRegistry.CRISTALLO_ARCANO) return carica("herb_cristallo_arcano.png");
        if (item == ItemRegistry.ESSENZA_DRAGO) return carica("herb_essenza_drago.png");
        if (item == ItemRegistry.BASTONE_MAGICO) return carica("item_bastone_magico.png");

        if (item instanceof it.unicam.cs.mpgc.rpg130957.model.items.Potion) {
            return carica("item_pozione.png");
        }

        return null;
    }

    /** Icona generica da usare per un'erba raccoglibile quando non ha ancora uno sprite dedicato. */
    public static Image getIconaErbaGenerica() {
        return carica("icon_erba_generica.png");
    }

    private static Image carica(String nomeFile) {
        return CACHE.computeIfAbsent(nomeFile, nome -> {
            var stream = SpriteRegistry.class.getResourceAsStream("/sprites/" + nome);
            if (stream == null) return null;
            return new Image(stream);
        });
    }
}
