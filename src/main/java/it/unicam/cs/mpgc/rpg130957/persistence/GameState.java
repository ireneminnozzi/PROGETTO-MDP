package it.unicam.cs.mpgc.rpg130957.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameState {


    public int salute;
    public int mana;
    public int livello;
    public int esperienza;
    public String posizione;



    public Map<String, Integer> inventario = new HashMap<>();


    public List<String> abilitaSbloccate = new ArrayList<>();


    public String questAttiva;
    public Map<String, Integer> progressoQuest = new HashMap<>();


    public boolean bossSconfitto;


    public Map<String, Integer> nemiciPerArea = new HashMap<>();


    public List<String> magieSbloccate = new ArrayList<>();


    public List<String> ricetteSbloccate = new ArrayList<>();

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