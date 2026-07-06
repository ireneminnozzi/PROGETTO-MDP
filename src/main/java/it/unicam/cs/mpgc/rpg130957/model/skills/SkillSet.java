package it.unicam.cs.mpgc.rpg130957.model.skills;

import java.util.HashMap;
import java.util.Map;

public class SkillSet {

    private static final Map<String, Skill> skills = new HashMap<>();

    public static final Skill POTENZA_ARCANA =
            new Skill("Potenza Arcana", "Aumenta il danno delle magie del 20%.");

    public static final Skill MAESTRIA_ARMI =
            new Skill("Maestria delle Armi", "Aumenta il danno delle armi del 15%.");

    public static final Skill RESISTENZA_MISTICA =
            new Skill("Resistenza Mistica", "Riduce i danni subiti del 10%.");

    // 🔥 Blocco statico per registrare automaticamente le skill
    static {
        skills.put(POTENZA_ARCANA.getNome(), POTENZA_ARCANA);
        skills.put(MAESTRIA_ARMI.getNome(), MAESTRIA_ARMI);
        skills.put(RESISTENZA_MISTICA.getNome(), RESISTENZA_MISTICA);
    }

    // 🔥 Metodo per cercare una skill per nome
    public static Skill getByName(String nome) {
        return skills.get(nome);
    }

    // 🔥 Metodo per ottenere tutte le skill (opzionale)
    public static Map<String, Skill> getAll() {
        return skills;
    }
}
