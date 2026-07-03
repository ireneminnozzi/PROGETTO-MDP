package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;

import java.util.List;

public class QuestAvanzata {

    private final String nome;
    private final List<QuestObjective> obiettivi;
    private final int ricompensaOro;

    public QuestAvanzata(String nome, List<QuestObjective> obiettivi, int ricompensaOro) {
        this.nome = nome;
        this.obiettivi = obiettivi;
        this.ricompensaOro = ricompensaOro;
    }

    public boolean èCompletata() {
        return obiettivi.stream().allMatch(QuestObjective::èCompletato);
    }

    public void completa(Wallet wallet) {
        wallet.aggiungiOro(ricompensaOro);
        System.out.println("Quest completata! Hai ottenuto " + ricompensaOro + " oro.");
    }

    public String getNome() { return nome; }
    public List<QuestObjective> getObiettivi() { return obiettivi; }
}
