package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;

public class QuestObjective {

    public enum Tipo {
        RACCOGLI,
        SCONFIGGI
    }

    private final String nome;
    private final Tipo tipo;
    private final Item itemRichiesto;
    private final EnemyType nemicoRichiesto;
    private final int quantitaRichiesta;

    private int progresso;

    private QuestObjective(String nome, Tipo tipo, Item itemRichiesto, EnemyType nemicoRichiesto, int quantitaRichiesta) {
        this.nome = nome;
        this.tipo = tipo;
        this.itemRichiesto = itemRichiesto;
        this.nemicoRichiesto = nemicoRichiesto;
        this.quantitaRichiesta = quantitaRichiesta;
        this.progresso = 0;
    }

    public static QuestObjective sconfiggi(EnemyType tipoNemico, int quantita) {
        return new QuestObjective(
                "Sconfiggi " + quantita + "x " + tipoNemico.name(),
                Tipo.SCONFIGGI,
                null,
                tipoNemico,
                quantita
        );
    }


    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getProgresso() {
        return progresso;
    }

    public int getQuantitaRichiesta() {
        return quantitaRichiesta;
    }

    public Item getItemRichiesto() {
        return itemRichiesto;
    }

    public EnemyType getNemicoRichiesto() {
        return nemicoRichiesto;
    }

    public void setProgresso(int valore) {
        this.progresso = valore;
    }

    public boolean èCompletato() {
        return progresso >= quantitaRichiesta;
    }

    public void avanza() {
        progresso++;
    }
}
