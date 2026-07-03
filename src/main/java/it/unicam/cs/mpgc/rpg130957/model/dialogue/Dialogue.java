package it.unicam.cs.mpgc.rpg130957.model.dialogue;

import java.util.List;

public class Dialogue {

    private final String npc;
    private final List<String> battute;

    public Dialogue(String npc, List<String> battute) {
        this.npc = npc;
        this.battute = battute;
    }

    public void start() {
        System.out.println("💬 " + npc + ":");
        for (String frase : battute) {
            System.out.println("  " + frase);
        }
    }
}
