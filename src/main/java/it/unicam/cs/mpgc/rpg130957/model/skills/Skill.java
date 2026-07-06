package it.unicam.cs.mpgc.rpg130957.model.skills;

import it.unicam.cs.mpgc.rpg130957.model.skills.*;

public class Skill {

    private final String nome;
    private final String descrizione;

    public Skill(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }



}
