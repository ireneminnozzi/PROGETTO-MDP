package it.unicam.cs.mpgc.rpg130957.persistence.json;

import java.util.Map;

/**
 * DTO che rappresenta lo stato del gioco da salvare in JSON.
 */
public class GameState {

    public int salute;
    public int mana;
    public int livello;
    public int esperienza;

    public Map<String, Integer> inventario;

    public String posizione;

    public GameState() {}

    public GameState(int salute, int mana, int livello, int esperienza,
                     Map<String, Integer> inventario, String posizione) {
        this.salute = salute;
        this.mana = mana;
        this.livello = livello;
        this.esperienza = esperienza;
        this.inventario = inventario;
        this.posizione = posizione;
    }
}
