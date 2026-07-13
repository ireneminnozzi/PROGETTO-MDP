package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.magic.Spell;
import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {

    private static final Map<String, Spell> spells = new HashMap<>();

    public static Spell getByName(String nome) {
        return spells.get(nome);
    }

}
