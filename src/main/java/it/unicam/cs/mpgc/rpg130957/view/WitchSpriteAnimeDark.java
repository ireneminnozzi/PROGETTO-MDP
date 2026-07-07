package it.unicam.cs.mpgc.rpg130957.view;


import javafx.scene.image.Image;

public class WitchSpriteAnimeDark implements WitchSprite {

    @Override
    public Image getIdle() {
        return load("witch_dark_idle.png");
    }

    @Override
    public Image getWalk() {
        return load("witch_dark_walk.png");
    }

    @Override
    public Image getCollect() {
        return load("witch_dark_collect.png");
    }

    @Override
    public Image getCombat() {
        return load("witch_dark_combat.png");
    }

    private Image load(String file) {
        return new Image(getClass().getResourceAsStream("/sprites/" + file));
    }
}
