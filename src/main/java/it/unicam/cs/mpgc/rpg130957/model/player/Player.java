package it.unicam.cs.mpgc.rpg130957.model.player;

import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Potion;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;
import it.unicam.cs.mpgc.rpg130957.model.magic.Spell;
import it.unicam.cs.mpgc.rpg130957.model.skills.SkillTree;

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

    public String getNome() { return nome; }
    public int getSalute() { return salute; }
    public int getMana() { return mana; }
    public Inventario getInventario() { return inventario; }
    public int getLivello() { return livello; }
    public int getEsperienza() { return esperienza; }

    public boolean isVivo() {
        return salute > 0;
    }

    public void subisciDanno(int danno) {
        salute -= danno;
        if (salute < 0) salute = 0;
    }

    public void usaPozione(Potion p) {
        salute += 20;
        mana += 10;
    }

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

    public boolean puoLanciare(Spell spell) {
        return mana >= spell.getCostoMana();
    }

    public void lancia(Spell spell, Enemy enemy) {
        if (!puoLanciare(spell)) {
            System.out.println("Non hai abbastanza mana per " + spell.getNome());
            return;
        }
        mana -= spell.getCostoMana();
        enemy.subisciDanno(spell.getDanno() + livello * 2);
        System.out.println("Hai lanciato " + spell.getNome() + "!");
    }

    private final SkillTree skillTree = new SkillTree();

    public SkillTree getSkillTree() {
        return skillTree;
    }


}
