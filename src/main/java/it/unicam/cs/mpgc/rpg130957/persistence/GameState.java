package it.unicam.cs.mpgc.rpg130957.persistence;

import java.util.List;
import java.util.Map;


public class GameState {


    public int salute;
    public int mana;
    public int livello;
    public int esperienza;
    public String posizione;

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public Map<String, Integer> inventario;


    public List<String> abilitaSbloccate;


    public String questAttiva;
    public Map<String, Integer> progressoQuest;


    public boolean bossSconfitto;


    public Map<String, Integer> nemiciPerArea;


    public List<String> magieSbloccate;


    public List<String> ricetteSbloccate;

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
