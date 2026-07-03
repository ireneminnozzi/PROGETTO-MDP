package it.unicam.cs.mpgc.rpg130957.model.magic;

public class Spell {

    private final String nome;
    private final int costoMana;
    private final int danno;

    public Spell(String nome, int costoMana, int danno) {
        this.nome = nome;
        this.costoMana = costoMana;
        this.danno = danno;
    }

    public String getNome() { return nome; }
    public int getCostoMana() { return costoMana; }
    public int getDanno() { return danno; }
}
