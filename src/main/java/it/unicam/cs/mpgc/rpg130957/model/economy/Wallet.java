package it.unicam.cs.mpgc.rpg130957.model.economy;



public class Wallet {

    private int gold;

    public Wallet(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public boolean spend(int amount) {
        if (gold < amount) return false;
        gold -= amount;
        return true;
    }

    public void earn(int amount) {
        gold += amount;
    }

    public void aggiungiOro(int oro) {
        this.gold += oro;
    }

}
