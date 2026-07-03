package it.unicam.cs.mpgc.rpg130957.model.skills;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {

    private final List<Skill> abilitàSbloccate = new ArrayList<>();

    public void sblocca(Skill skill) {
        abilitàSbloccate.add(skill);
        System.out.println("🌟 Abilità sbloccata: " + skill.getNome());
    }

    public void mostra() {
        System.out.println("=== SKILL TREE ===");
        if (abilitàSbloccate.isEmpty()) {
            System.out.println("Nessuna abilità sbloccata.");
            return;
        }
        for (Skill s : abilitàSbloccate) {
            System.out.println("- " + s.getNome() + ": " + s.getDescrizione());
        }
    }
}
