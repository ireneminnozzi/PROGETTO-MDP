package it.unicam.cs.mpgc.rpg130957.model.player;

import it.unicam.cs.mpgc.rpg130957.model.Inventario.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;

public class Player {

    private final String nome;
    private int salute;
    private int mana;
    private final Inventario inventario;

    private int livello = 1;
    private int esperienza = 0;
    private int esperienzaNecessaria = 50;

    public Player(String nome) {
        this.nome = nome;
        this.salute = 100;
        this.mana = 50;
        this.inventario = new Inventario();
    }

    // --- STATISTICHE ---
    public String getNome() { return nome; }
    public int getSalute() { return salute; }
    public int getMana() { return mana; }
    public Inventario getInventario() { return inventario; }
    public int getLivello() { return livello; }
    public int getEsperienza() { return esperienza; }

    public boolean isVivo() {
        return salute > 0;
    }

    // --- RACCOLTA ---
    public void raccogli(Item item) {
        inventario.aggiungiIngrediente(item, 1);
    }

    // --- POZIONI ---
    public void usaPozione(Potion p) {
        // qui potresti usare descrizione/rarità per effetti diversi
        salute += 20;
        mana += 10;
    }

    // --- COMBATTIMENTO ---
    public void attacca(Enemy enemy, Weapon arma) {
        int danno = arma.getDanno() + (livello * 2);
        enemy.subisciDanno(danno);
        if (enemy.isSconfitto()) {
            guadagnaXP(20);
        }
    }

    // --- LIVELLI ---
    public void guadagnaXP(int xp) {
        esperienza += xp;
        if (esperienza >= esperienzaNecessaria) {
            levelUp();
        }
    }

    private void levelUp() {
        esperienza -= esperienzaNecessaria;
        livello++;
        esperienzaNecessaria = (int)(esperienzaNecessaria * 1.5);

        salute += 20;
        mana += 10;
        System.out.println("✨ Hai raggiunto il livello " + livello + "!");
    }
}

