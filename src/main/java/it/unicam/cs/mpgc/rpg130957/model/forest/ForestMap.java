package it.unicam.cs.mpgc.rpg130957.model.forest;

import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;

public class ForestMap {

    private ForestArea radura;
    private ForestArea fiume;
    private ForestArea palude;
    private ForestArea alberoAntico;
    private ForestArea grotta;
    private ForestArea rovine;
    private ForestArea sentieroLupo;
    private ForestArea lagoLuna;

    public ForestMap() {
        creaMappa();
    }

    private void creaMappa() {

        // Creazione aree
        radura = new ForestArea("Radura del Bosco");
        fiume = new ForestArea("Sentiero del Fiume");
        palude = new ForestArea("Palude Nebbiosa");
        alberoAntico = new ForestArea("Albero Antico");
        grotta = new ForestArea("Grotta Oscura");
        rovine = new ForestArea("Rovine Elfiche");
        sentieroLupo = new ForestArea("Sentiero del Lupo");
        lagoLuna = new ForestArea("Lago della Luna");

        // Collegamenti
        radura.collega(fiume);
        fiume.collega(radura);

        fiume.collega(palude);
        palude.collega(fiume);

        fiume.collega(alberoAntico);
        alberoAntico.collega(fiume);

        palude.collega(grotta);
        grotta.collega(palude);

        alberoAntico.collega(rovine);
        rovine.collega(alberoAntico);

        radura.collega(sentieroLupo);
        sentieroLupo.collega(radura);

        rovine.collega(lagoLuna);
        lagoLuna.collega(rovine);

        // RISORSE
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
        radura.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);

        fiume.aggiungiRisorsa(ItemRegistry.FUNGHI_SPETTRALI);

        palude.aggiungiRisorsa(ItemRegistry.PETALO_ROSA_NERA);
        palude.aggiungiRisorsa(ItemRegistry.FIORE_LUNARE);

        alberoAntico.aggiungiRisorsa(ItemRegistry.POLVERE_STELLARE);

        grotta.aggiungiRisorsa(ItemRegistry.CRISTALLO_ARCANO);

        rovine.aggiungiRisorsa(ItemRegistry.LACRIMA_FENICE);

        lagoLuna.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);

        // NEMICI
        radura.aggiungiNemico(new Enemy("Lupo Giovane", EnemyType.LUPO, 15, 4));

        fiume.aggiungiNemico(new Enemy("Goblin Ladro", EnemyType.GOBLIN, 18, 5));

        palude.aggiungiNemico(new Enemy("Spirito della Palude", EnemyType.SPIRITO, 25, 7));

        alberoAntico.aggiungiNemico(new Enemy("Guardiano Antico", EnemyType.SPIRITO, 30, 8));

        grotta.aggiungiNemico(new Enemy("Scheletro Guerriero", EnemyType.SCHELETRO, 35, 10));

        rovine.aggiungiNemico(new Enemy("Spirito Elfico", EnemyType.SPIRITO, 40, 12));

        sentieroLupo.aggiungiNemico(new Enemy("Lupo Alfa", EnemyType.LUPO, 30, 9));

        lagoLuna.aggiungiNemico(new Enemy("Drago della Luna", EnemyType.DRAGO, 80, 20));
    }

    public ForestArea getStartArea() {
        return radura;
    }
}
