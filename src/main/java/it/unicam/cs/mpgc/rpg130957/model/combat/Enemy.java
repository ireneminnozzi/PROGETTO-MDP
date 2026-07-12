package it.unicam.cs.mpgc.rpg130957.model.combat;



public class Enemy {

    private String nome;
    private EnemyType tipo;
    private int salute;
    private final int saluteMassima;
    private int attacco;

    public Enemy(String nome, EnemyType tipo, int salute, int attacco) {
        this.nome = nome;
        this.tipo = tipo;
        this.salute = salute;
        this.saluteMassima = salute;
        this.attacco = attacco;
    }

    public String getNome() { return nome; }
    public EnemyType getTipo() { return tipo; }
    public int getSalute() { return salute; }
    public int getSaluteMassima() { return saluteMassima; }
    public int getAttacco() { return attacco; }

    public void subisciDanno(int danno) {
        salute = Math.max(0, salute - danno);
    }

    public boolean isSconfitto() {
        return salute <= 0;
    }

    /** Crea una copia dello stesso tipo di nemico, usata per farlo ricomparire. */

    public Enemy clona() {
        return new Enemy(nome, tipo, saluteMassima, attacco);
    }
}
