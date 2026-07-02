package it.unicam.cs.mpgc.rpg130957;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;

public class Main {

    public static void main(String[] args) {

        Player player = new Player("Strega del Bosco");
        GameController game = new GameController();

        System.out.println("🌙 Benvenuta, " + player.getNome());
        System.out.println("Ti trovi in: " + game.getPosizione().getNome());

        // Raccogli una risorsa
        if (game.raccogli()) {
            System.out.println("Hai raccolto una risorsa nella zona: " + game.getPosizione().getNome());
        }

        // Combatti il primo nemico con il bastone magico
        Weapon arma = ItemRegistry.BASTONE_MAGICO;
        boolean vinto = game.combatti(arma);
        System.out.println(vinto ? "Hai vinto il combattimento!" : "Hai perso il combattimento...");

        System.out.println("Oro attuale: " + game.getWallet().getGold());
        System.out.println("Inventario: " + game.getInventario().getTutti());
        System.out.println("Fine demo.");
    }
}

