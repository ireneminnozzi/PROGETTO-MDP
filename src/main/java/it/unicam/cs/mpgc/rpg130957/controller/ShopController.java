package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;


public class ShopController {

    private final Inventario inventario;
    private final Wallet wallet;

    public ShopController(Inventario inventario, Wallet wallet) {
        this.inventario = inventario;
        this.wallet = wallet;
    }

    public boolean compra(Item item, int quantita) {
        int costoTotale = item.getPrezzo() * quantita;

        if (!wallet.spend(costoTotale)) {
            return false;
        }

        inventario.aggiungiIngrediente(item, quantita);
        return true;
    }

    public boolean vendi(Item item, int quantita) {
        if (!inventario.rimuoviIngrediente(item, quantita)) {
            return false;
        }

        int guadagno = item.getPrezzo() * quantita;
        wallet.earn(guadagno);
        return true;
    }

    public int getOro() {
        return wallet.getGold();
    }

    public Inventario getInventario() {
        return inventario;
    }
}

