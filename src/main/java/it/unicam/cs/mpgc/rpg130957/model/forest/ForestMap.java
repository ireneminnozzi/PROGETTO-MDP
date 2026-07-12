package it.unicam.cs.mpgc.rpg130957.model.forest;

import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.registry.ForestRegistry;

public class ForestMap {

    private ForestArea radura;
    private ForestArea fiume;
    private ForestArea palude;
    private ForestArea grotta;
    private ForestArea rovine;

    public ForestMap() {
        creaMappa();
    }

    private void creaMappa() {

        // Creazione aree
        radura = new ForestArea("Radura del Bosco");
        fiume = new ForestArea("Sentiero del Fiume");
        palude = new ForestArea("Palude Nebbiosa");
        grotta = new ForestArea("Grotta Oscura");
        rovine = new ForestArea("Rovine Elfiche");


        // Collegamenti
        radura.collega(fiume);
        fiume.collega(radura);

        fiume.collega(palude);
        palude.collega(fiume);


        palude.collega(grotta);
        grotta.collega(palude);

        grotta.collega(rovine);
        rovine.collega(radura);


        // RISORSE — quantità abbondanti in ogni area, così le ricette sono sempre craftabili
        // e raccogliere ha senso anche dopo la prima visita.
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
        radura.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        radura.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        radura.aggiungiRisorsa(ItemRegistry.MANDRAGORA);

        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);
        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);
        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);
        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);
        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);
        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);

        palude.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        palude.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        palude.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        palude.aggiungiRisorsa(ItemRegistry.MANDRAGORA);
        palude.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        palude.aggiungiRisorsa(ItemRegistry.ELISIR_SUPREMO);
        palude.aggiungiRisorsa(ItemRegistry.MANDRAGORA);

        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
        grotta.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);

        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);
        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);
        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);
        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);
        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);
        rovine.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);


        // NEMICI
        radura.aggiungiNemico(new Enemy("Lupo Solitario", EnemyType.LUPO, 15, 4));

        fiume.aggiungiNemico(new Enemy("Spirito Del Fiume", EnemyType.SPIRITO, 18, 5));

        palude.aggiungiNemico(new Enemy("Goblin Ladro", EnemyType.GOBLIN, 25, 7));

        grotta.aggiungiNemico(new Enemy("Drago Della Luna", EnemyType.DRAGO, 35, 10));

        rovine.aggiungiNemico(new Enemy("Scheletro dispettoso", EnemyType.SCHELETRO, 40, 12));

        ForestRegistry.registra(radura);
        ForestRegistry.registra(fiume);
        ForestRegistry.registra(palude);
        ForestRegistry.registra(grotta);
        ForestRegistry.registra(rovine);

    }

    public ForestArea getStartArea() {
        return radura;
    }
}
