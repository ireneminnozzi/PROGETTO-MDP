package it.unicam.cs.mpgc.rpg130957.model.inventory;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import java.util.HashMap;
import java.util.Map;

// Contiene gli item posseduti dal giocatore.
public class Inventario {

    private final Map<Item, Integer> ingredienti;

    public Inventario() {
        ingredienti = new HashMap<>();
    }

    // --- AGGIUNTA ---
    public void aggiungiIngrediente(Item item, int quantita) {
        ingredienti.put(item, ingredienti.getOrDefault(item, 0) + quantita);
    }

    // --- RIMOZIONE ---
    public boolean rimuoviIngrediente(Item item, int quantita) {
        if (!ingredienti.containsKey(item)) return false;
        int attuale = ingredienti.get(item);
        if (attuale < quantita) return false;
        ingredienti.put(item, attuale - quantita);
        if (ingredienti.get(item) == 0) {
            ingredienti.remove(item);
        }
        return true;
    }


    public void clear() {
        ingredienti.clear();
    }


    // --- CONTROLLO ---
    public boolean contiene(Item item) {
        return ingredienti.containsKey(item);
    }

    public int getQuantita(Item item) {
        return ingredienti.getOrDefault(item, 0);
    }

    // --- ACCESSO COMPLETO ---
    public Map<Item, Integer> getTutti() {
        return ingredienti;
    }
}
