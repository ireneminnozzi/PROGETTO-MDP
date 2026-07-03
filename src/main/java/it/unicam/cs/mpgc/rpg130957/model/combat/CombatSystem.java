package it.unicam.cs.mpgc.rpg130957.model.combat;

import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;

//Gestisce il combattimento tra la strega e un nemico usando un’arma.

public class CombatSystem {

    public static boolean combatti(Enemy enemy, Weapon arma, Player player) {

        int dannoGiocatore = arma.getDanno();

        while (!enemy.isSconfitto()) {
            enemy.subisciDanno(dannoGiocatore);

            if (enemy.isSconfitto()) return true;
        }

        return false;
    }
}
