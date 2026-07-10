package it.unicam.cs.mpgc.rpg130957.model.forest;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;

import java.util.ArrayList;
import java.util.List;

public class ForestArea {

    private String nome;
    private List<Item> risorse;
    private List<Enemy> nemici;
    private List<ForestArea> collegamenti;

    // Modello originale dell'area: usato per far ricomparire erbe e mostri
    // quando l'area si svuota (il giocatore la raccoglie/pulisce del tutto).
    private final List<Item> risorseIniziali = new ArrayList<>();
    private final List<Enemy> nemiciIniziali = new ArrayList<>();

    public ForestArea(String nome) {
        this.nome = nome;
        this.risorse = new ArrayList<>();
        this.nemici = new ArrayList<>();
        this.collegamenti = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Item> getRisorse() { return risorse; }
    public List<Enemy> getNemici() { return nemici; }
    public List<ForestArea> getCollegamenti() { return collegamenti; }

    public void aggiungiRisorsa(Item item) {
        risorse.add(item);
        risorseIniziali.add(item);
    }

    public void aggiungiNemico(Enemy enemy) {
        nemici.add(enemy);
        nemiciIniziali.add(enemy);
    }

    public void collega(ForestArea altra) { collegamenti.add(altra); }

    /**
     * Fa ricomparire erbe e mostri quando l'area è stata completamente
     * svuotata (nessuna risorsa e nessun nemico rimasti). Va chiamato
     * ogni volta che ci si sposta in quest'area.
     */
    public void rigeneraSeVuota() {
        if (risorse.isEmpty() && !risorseIniziali.isEmpty()) {
            risorse.addAll(risorseIniziali);
        }
        if (nemici.isEmpty() && !nemiciIniziali.isEmpty()) {
            for (Enemy modello : nemiciIniziali) {
                nemici.add(modello.clona());
            }
        }
    }

    // ricostruire i nemici dal JSON
    public void clearNemici() {
        nemici.clear();
    }

    // per ricostruire il numero di nemici dal JSON
    public void setNemici(int count) {
        nemici.clear();
        for (int i = 0; i < count; i++) {
            nemici.add(new Enemy("Nemico", null, 10, 3)); // puoi personalizzarlo
        }
    }
}
