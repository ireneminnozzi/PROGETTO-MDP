package it.unicam.cs.mpgc.rpg130957.model.player;

import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;

public class PlayerHUD {

    public static void mostraHUD(Player player) {
        System.out.println("\n=== HUD GIOCATORE ===");
        System.out.println("Nome: " + player.getNome());
        System.out.println("Salute: " + player.getSalute());
        System.out.println("Mana: " + player.getMana());
        System.out.println("Livello: " + player.getLivello());
        System.out.println("XP: " + player.getEsperienza());
    }

    public static void mostraInventario(Inventario inventario) {
        System.out.println("\n=== INVENTARIO ===");
        if (inventario.getTutti().isEmpty()) {
            System.out.println("Inventario vuoto.");
            return;
        }

        for (Item item : inventario.getTutti().keySet()) {
            int qty = inventario.getQuantita(item);
            System.out.println(item.getNome() + " x" + qty);
        }
    }
}
