package it.unicam.cs.mpgc.rpg130957.model.items;

//Rappresenta un oggetto craftato tramite ricette.

public class Potion extends AbstractItem {

    public Potion(String nome, String descrizione, int prezzo, Rarity rarity) {
        super(nome, descrizione, prezzo, rarity);
    }
}
