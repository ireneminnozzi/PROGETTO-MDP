package it.unicam.cs.mpgc.rpg130957.model.combat;

public enum BossType {
    DRAGO_FINALE(400, 45),
    DRUIDO_INTRO(250, 30);

    private final int saluteBase;
    private final int dannoBase;

    BossType(int saluteBase, int dannoBase) {
        this.saluteBase = saluteBase;
        this.dannoBase = dannoBase;
    }

    public int getSaluteBase() { return saluteBase; }
    public int getDannoBase() { return dannoBase; }
}
