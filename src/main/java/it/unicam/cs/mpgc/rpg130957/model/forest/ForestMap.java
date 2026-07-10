package it.unicam.cs.mpgc.rpg130957.model.forest;

import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.registry.ForestRegistry;

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
        //alberoAntico = new ForestArea("Albero Antico");
        grotta = new ForestArea("Grotta Oscura");
        rovine = new ForestArea("Rovine Elfiche");
        //sentieroLupo = new ForestArea("Sentiero del Lupo");
        //lagoLuna = new ForestArea("Lago della Luna");

        //alcune aree della mappa sono state aggiunte con l'dea di espandere iin futuro il gioco

        // Collegamenti
        radura.collega(fiume);
        fiume.collega(radura);

        fiume.collega(palude);
        palude.collega(fiume);

//        fiume.collega(alberoAntico);
//        alberoAntico.collega(fiume);

        palude.collega(grotta);
        grotta.collega(palude);

        grotta.collega(rovine);
        rovine.collega(radura);

//        alberoAntico.collega(rovine);
//        rovine.collega(alberoAntico);
//
//        radura.collega(sentieroLupo);
//        sentieroLupo.collega(radura);
//
//        rovine.collega(lagoLuna);
//        lagoLuna.collega(rovine);

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

//        alberoAntico.aggiungiRisorsa(ItemRegistry.POLVERE_STELLARE);
//        alberoAntico.aggiungiRisorsa(ItemRegistry.POLVERE_STELLARE);
//        alberoAntico.aggiungiRisorsa(ItemRegistry.POLVERE_STELLARE);

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
//        rovine.aggiungiRisorsa(ItemRegistry.CORNO_UNICORNO);
//        rovine.aggiungiRisorsa(ItemRegistry.CORNO_UNICORNO);

//        sentieroLupo.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
//        sentieroLupo.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
//        sentieroLupo.aggiungiRisorsa(ItemRegistry.ERBA_MAGICA);
//
//        lagoLuna.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
//        lagoLuna.aggiungiRisorsa(ItemRegistry.ESSENZA_DRAGO);
//        lagoLuna.aggiungiRisorsa(ItemRegistry.FIORE_LUNARE);
//        lagoLuna.aggiungiRisorsa(ItemRegistry.FIORE_LUNARE);
//        lagoLuna.aggiungiRisorsa(ItemRegistry.FIORE_LUNARE);

        // NEMICI
        radura.aggiungiNemico(new Enemy("Lupo Solitario", EnemyType.LUPO, 15, 4));

        fiume.aggiungiNemico(new Enemy("Spirito Del Fiume", EnemyType.SPIRITO, 18, 5));

        palude.aggiungiNemico(new Enemy("Goblin Ladro", EnemyType.GOBLIN, 25, 7));

//        alberoAntico.aggiungiNemico(new Enemy("Guardiano Antico", EnemyType.SPIRITO, 30, 8));

        grotta.aggiungiNemico(new Enemy("Drago Della Luna", EnemyType.DRAGO, 35, 10));

        rovine.aggiungiNemico(new Enemy("Scheletro disspettoso", EnemyType.SCHELETRO, 40, 12));

//        sentieroLupo.aggiungiNemico(new Enemy("Lupo Alfa", EnemyType.LUPO, 30, 9));

//        lagoLuna.aggiungiNemico(new Enemy("Drago della Luna", EnemyType.DRAGO, 80, 20));

        ForestRegistry.registra(radura);
        ForestRegistry.registra(fiume);
        ForestRegistry.registra(palude);
//        ForestRegistry.registra(alberoAntico);
        ForestRegistry.registra(grotta);
        ForestRegistry.registra(rovine);
//        ForestRegistry.registra(sentieroLupo);
//        ForestRegistry.registra(lagoLuna);

    }

    public ForestArea getStartArea() {
        return radura;
    }
}
