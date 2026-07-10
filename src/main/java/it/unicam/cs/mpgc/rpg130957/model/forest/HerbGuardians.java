package it.unicam.cs.mpgc.rpg130957.model.forest;

import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType;

import java.util.Map;

public class HerbGuardians {

    private static final Map<Item, EnemyType> GUARDIANI = Map.of(
            ItemRegistry.ERBA_MAGICA, EnemyType.LUPO,
            ItemRegistry.FUNGHI_SPETTRALI, EnemyType.SPIRITO,
            ItemRegistry.MANDRAGORA, EnemyType.GOBLIN,
            ItemRegistry.CRISTALLO_ARCANO, EnemyType.SCHELETRO,
            ItemRegistry.ESSENZA_DRAGO, EnemyType.DRAGO
    );

    public static EnemyType getGuardiano(Item erba) {
        return GUARDIANI.get(erba);
    }
}
