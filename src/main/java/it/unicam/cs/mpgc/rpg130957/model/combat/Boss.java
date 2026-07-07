//package it.unicam.cs.mpgc.rpg130957.model.combat;
//
//public class Boss {
//
//    private final BossType tipo;
//    private int salute;
//
//    public Boss(BossType tipo) {
//        this.tipo = tipo;
//        this.salute = tipo.getSaluteBase();
//    }
//
//    public BossType getTipo() { return tipo; }
//    public int getSalute() { return salute; }
//
//    public void subisciDanno(int danno) {
//        salute -= danno;
//        if (salute < 0) salute = 0;
//    }
//
//    public boolean isSconfitto() {
//        return salute <= 0;
//    }
//}
