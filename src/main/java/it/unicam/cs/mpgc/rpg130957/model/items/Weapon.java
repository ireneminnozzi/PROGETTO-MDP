package it.unicam.cs.mpgc.rpg130957.model.items;


public class Weapon extends AbstractItem {

    private int danno;

    public Weapon(String nome, String descrizione,  Rarity rarity, int danno) {
        super(nome, descrizione, rarity);
        this.danno = danno;
    }

    public int getDanno() {
        return danno;
    }
}
