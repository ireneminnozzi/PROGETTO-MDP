package it.unicam.cs.mpgc.rpg130957.model.items;

import java.util.Objects;

public abstract class AbstractItem implements Item {

    private String nome;
    private String descrizione;
    private Rarity rarity;

    public AbstractItem(String nome, String descrizione,  Rarity rarity) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.rarity = rarity;
    }

    @Override
    public String getNome() {
        return nome;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractItem)) return false;
        AbstractItem that = (AbstractItem) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return nome + " (" + rarity + ") - " + descrizione;
    }
}