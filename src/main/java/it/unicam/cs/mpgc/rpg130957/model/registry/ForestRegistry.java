package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;

import java.util.HashMap;
import java.util.Map;

public class ForestRegistry {

    private static final Map<String, ForestArea> areas = new HashMap<>();


    public static void registra(ForestArea area) {
        areas.put(area.getNome(), area);
    }


    public static ForestArea getByName(String nome) {
        return areas.get(nome);
    }
}
