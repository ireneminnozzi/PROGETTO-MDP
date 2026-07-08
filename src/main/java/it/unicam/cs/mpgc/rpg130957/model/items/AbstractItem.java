package it.unicam.cs.mpgc.rpg130957.model.items;

import java.util.Objects;

public abstract class AbstractItem implements Item {

    protected String nome;
    protected String descrizione;
    protected int prezzo;
    protected Rarity rarity;

    public AbstractItem(String nome, String descrizione, int prezzo, Rarity rarity) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.rarity = rarity;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public int getPrezzo() {
        return prezzo;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
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