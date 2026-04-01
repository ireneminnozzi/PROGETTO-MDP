package it.unicam.cs.mpgc.rpg130957.model.items;

//Definisce cosa significa essere un oggetto del gioco.

public interface Item {
    String getNome();
    int getPrezzo();
    Rarity getRarity();
}
