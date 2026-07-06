package it.unicam.cs.mpgc.rpg130957.model.registry;

import it.unicam.cs.mpgc.rpg130957.model.skills.Skill;
import java.util.HashMap;
import java.util.Map;

public class SkillRegistry {

    private static final Map<String, Skill> skills = new HashMap<>();

    public static void register(Skill skill) {
        skills.put(skill.getNome(), skill);
    }

    public static Skill getByName(String nome) {
        return skills.get(nome);
    }

    public static Map<String, Skill> getAll() {
        return skills;
    }
}
