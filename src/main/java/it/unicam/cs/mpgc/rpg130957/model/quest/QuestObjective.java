package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;

public class QuestObjective {

    public enum Tipo {
        RACCOGLI,
        SCONFIGGI
    }

    private final Tipo tipo;
    private final Item itemRichiesto;
    private final EnemyType nemicoRichiesto;
    private final int quantitaRichiesta;
    private int progresso = 0;

    public static QuestObjective raccogli(Item item, int quantita) {
        return new QuestObjective(Tipo.RACCOGLI, item, null, quantita);
    }

    public static QuestObjective sconfiggi(EnemyType tipoNemico, int quantita) {
        return new QuestObjective(Tipo.SCONFIGGI, null, tipoNemico, quantita);
    }

    private QuestObjective(Tipo tipo, Item item, EnemyType nemico, int quantita) {
        this.tipo = tipo;
        this.itemRichiesto = item;
        this.nemicoRichiesto = nemico;
        this.quantitaRichiesta = quantita;
    }

    public boolean èCompletato() {
        return progresso >= quantitaRichiesta;
    }

    public void avanza() {
        progresso++;
    }

    public Tipo getTipo() { return tipo; }
    public Item getItemRichiesto() { return itemRichiesto; }
    public EnemyType getNemicoRichiesto() { return nemicoRichiesto; }
    public int getProgresso() { return progresso; }
    public int getQuantitaRichiesta() { return quantitaRichiesta; }
}
