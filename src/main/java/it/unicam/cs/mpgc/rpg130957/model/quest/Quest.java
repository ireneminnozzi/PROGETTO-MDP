package it.unicam.cs.mpgc.rpg130957.model.quest;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;

//Sistema opzionale di missioni: obiettivi, ricompense, progressione


public class Quest {

    private String nome;
    private String descrizione;
    private Item oggettoRichiesto;
    private int quantitaRichiesta;
    private int ricompensaOro;

    public Quest(String nome, String descrizione, Item oggettoRichiesto, int quantitaRichiesta, int ricompensaOro) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.oggettoRichiesto = oggettoRichiesto;
        this.quantitaRichiesta = quantitaRichiesta;
        this.ricompensaOro = ricompensaOro;
    }

    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public Item getOggettoRichiesto() { return oggettoRichiesto; }
    public int getQuantitaRichiesta() { return quantitaRichiesta; }
    public int getRicompensaOro() { return ricompensaOro; }
}
