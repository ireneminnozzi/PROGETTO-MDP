package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import java.util.HashMap;
import java.util.Map;

public class ItemsRegistry {

    private static final Map<String, Item> items = new HashMap<>();

    public static void register(Item item) {
        items.put(item.getNome(), item);
    }

    public static Item getByName(String nome) {
        return items.get(nome);
    }

    public static Map<String, Item> getAll() {
        return items;
    }
}
