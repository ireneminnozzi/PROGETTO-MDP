package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;
import it.unicam.cs.mpgc.rpg130957.model.skills.SkillSet;

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

    public void completa(Wallet wallet, Player player) {
        wallet.aggiungiOro(ricompensaOro);
        System.out.println("Quest completata! Hai ottenuto " + ricompensaOro + " oro.");

        switch (this.nome) {
            case "Rituale della Luna":
                player.getSkillTree().sblocca(SkillSet.POTENZA_ARCANA);
                break;

            case "Prova del Guerriero":
                player.getSkillTree().sblocca(SkillSet.MAESTRIA_ARMI);
                break;

            case "Benedizione degli Spiriti":
                player.getSkillTree().sblocca(SkillSet.RESISTENZA_MISTICA);
                break;
        }
    }

    public String getNome() { return nome; }
    public List<QuestObjective> getObiettivi() { return obiettivi; }


    public void setProgresso(String nomeObiettivo, int valore) {
        for (QuestObjective obj : obiettivi) {
            if (obj.getNome().equals(nomeObiettivo)) {
                obj.setProgresso(valore);
            }
        }
    }
}
