package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.items.ItemRegistry;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.magic.Spell;
import it.unicam.cs.mpgc.rpg130957.model.magic.SpellSet;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class CombatUI {

    private final Stage stage;
    private final GameController game;
    private final Player player;
    private final Enemy nemico;
    private final Item erba;      // può essere null se il combattimento non è legato a un'erba
    private final Runnable onFine;

    private final Label logLabel = new Label();
    private final Label playerLabel = new Label();
    private final Label nemicoLabel = new Label();
    private final ProgressBar playerHpBar = new ProgressBar();
    private final ProgressBar nemicoHpBar = new ProgressBar();
    private final int nemicoSaluteMax;

    private final VBox azioniPrincipali = new VBox(10);
    private final VBox azioniMagia = new VBox(10);
    private final VBox root = new VBox(15);

    private boolean combattimentoFinito = false;

    private CombatUI(GameController game, Enemy nemico, Item erba, Runnable onFine) {
        this.game = game;
        this.player = game.getPlayer();
        this.nemico = nemico;
        this.erba = erba;
        this.onFine = onFine;
        this.nemicoSaluteMax = nemico.getSalute();
        this.stage = new Stage();
    }

    /** Combattimento scatenato dalla raccolta di un'erba custodita da un mostro. */
    public static void avviaCombattimentoErba(GameController game, Enemy nemico, Item erba, Runnable onFine) {
        new CombatUI(game, nemico, erba, onFine).mostra();
    }

    /** Combattimento generico contro un mostro non legato a un'erba. */
    public static void avviaCombattimento(GameController game, Enemy nemico, Runnable onFine) {
        new CombatUI(game, nemico, null, onFine).mostra();
    }

    private void mostra() {
        stage.setTitle("⚔️ Combattimento");
        stage.initModality(Modality.APPLICATION_MODAL);

        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #201830;");

        Label titolo = new Label("⚔️ " + nemico.getNome() + " ti sbarra la strada!");
        titolo.setStyle("-fx-text-fill: #ffd166; -fx-font-size: 16px;");

        playerHpBar.setPrefWidth(220);
        nemicoHpBar.setPrefWidth(220);

        VBox statoPlayer = new VBox(6);
        statoPlayer.setAlignment(Pos.CENTER);
        Image witchSprite = SpriteRegistry.getWitchFront();
        if (witchSprite != null) {
            ImageView witchView = new ImageView(witchSprite);
            witchView.setFitHeight(90);
            witchView.setPreserveRatio(true);
            statoPlayer.getChildren().add(witchView);
        }
        statoPlayer.getChildren().addAll(playerLabel, playerHpBar);

        VBox statoNemico = new VBox(6);
        statoNemico.setAlignment(Pos.CENTER);
        Image nemicoSprite = SpriteRegistry.getEnemyImage(nemico.getTipo());
        if (nemicoSprite != null) {
            ImageView nemicoView = new ImageView(nemicoSprite);
            nemicoView.setFitHeight(90);
            nemicoView.setPreserveRatio(true);
            statoNemico.getChildren().add(nemicoView);
        }
        statoNemico.getChildren().addAll(nemicoLabel, nemicoHpBar);

        HBox stato = new HBox(40, statoPlayer, statoNemico);
        stato.setAlignment(Pos.CENTER);

        logLabel.setWrapText(true);
        logLabel.setMaxWidth(420);
        logLabel.setStyle("-fx-text-fill: #cdbfe8;");

        costruisciAzioniPrincipali();
        costruisciAzioniMagia();
        azioniMagia.setVisible(false);
        azioniMagia.setManaged(false);

        root.getChildren().addAll(titolo, stato, logLabel, azioniPrincipali, azioniMagia);

        aggiornaStato("Il combattimento ha inizio! Scegli la tua mossa.");

        stage.setScene(new Scene(root, 480, 420));
        stage.show();
    }

    private void costruisciAzioniPrincipali() {
        Button attacca = new Button("⚔ Attacca");
        attacca.setOnAction(e -> turnoAttacco());

        Button magia = new Button("🔮 Magia");
        magia.setOnAction(e -> {
            azioniPrincipali.setVisible(false);
            azioniPrincipali.setManaged(false);
            azioniMagia.setVisible(true);
            azioniMagia.setManaged(true);
        });

        Button pozione = new Button(" Bevi pozione");
        pozione.setOnAction(e -> turnoPozione());

        Button fuggi = new Button(" Fuggi");
        fuggi.setOnAction(e -> fuggiDalCombattimento());

        FlowPane riga = new FlowPane(10, 10, attacca, magia, pozione, fuggi);
        riga.setAlignment(Pos.CENTER);
        azioniPrincipali.getChildren().add(riga);
        azioniPrincipali.setAlignment(Pos.CENTER);
    }

    private void costruisciAzioniMagia() {
        List<Spell> magie = List.of(
                SpellSet.FIAMMA_ARCANA,
                SpellSet.LANCIA_LUNARE,
                SpellSet.ESPLOSIONE_DRACONICA
        );

        FlowPane riga = new FlowPane(10, 10);
        riga.setAlignment(Pos.CENTER);
        for (Spell spell : magie) {
            Button bottone = new Button(spell.getNome() + " (" + spell.getCostoMana() + " mana)");
            bottone.setOnAction(e -> turnoMagia(spell));
            riga.getChildren().add(bottone);
        }

        Button indietro = new Button("⬅ Indietro");
        indietro.setOnAction(e -> {
            azioniMagia.setVisible(false);
            azioniMagia.setManaged(false);
            azioniPrincipali.setVisible(true);
            azioniPrincipali.setManaged(true);
        });

        azioniMagia.getChildren().addAll(riga, indietro);
        azioniMagia.setAlignment(Pos.CENTER);
    }

    private void turnoAttacco() {
        if (combattimentoFinito) return;
        Weapon arma = player.getArmaEquipaggiata() != null ? player.getArmaEquipaggiata() : ItemRegistry.BASTONE_MAGICO;
        int danno = arma.getDanno() + player.getLivello();
        nemico.subisciDanno(danno);

        String messaggio = "Colpisci " + nemico.getNome() + " con " + arma.getNome() + " (" + danno + " danni).";
        risolviTurno(messaggio);
    }

    private void turnoMagia(Spell spell) {
        if (combattimentoFinito) return;

        if (!player.puoLanciare(spell)) {
            aggiornaStato("Non hai abbastanza mana per lanciare " + spell.getNome() + "!");
            return;
        }

        player.lancia(spell, nemico);
        String messaggio = "Lanci " + spell.getNome() + " su " + nemico.getNome() + "!";

        azioniMagia.setVisible(false);
        azioniMagia.setManaged(false);
        azioniPrincipali.setVisible(true);
        azioniPrincipali.setManaged(true);

        risolviTurno(messaggio);
    }

    private void turnoPozione() {
        if (combattimentoFinito) return;

        if (game.getInventario().getQuantita(ItemRegistry.POZIONE_CURA) <= 0) {
            aggiornaStato("Non hai pozioni di cura nell'inventario!");
            return;
        }

        game.getInventario().rimuoviIngrediente(ItemRegistry.POZIONE_CURA, 1);
        player.usaPozione(ItemRegistry.POZIONE_CURA);

        risolviTurno("Bevi una Pozione di Cura e recuperi le forze.");
    }

    private void fuggiDalCombattimento() {
        if (combattimentoFinito) return;
        combattimentoFinito = true;
        stage.close();
        if (onFine != null) onFine.run();
    }

    /** Applica il messaggio del turno del giocatore, poi (se il nemico è vivo) fa contrattaccare il nemico. */
    private void risolviTurno(String messaggioGiocatore) {
        if (nemico.isSconfitto()) {
            aggiornaStato(messaggioGiocatore);
            vittoria();
            return;
        }

        player.subisciDanno(nemico.getAttacco());
        String messaggio = messaggioGiocatore + "\n" + nemico.getNome() + " ti colpisce per " + nemico.getAttacco() + " danni.";

        if (!player.isVivo()) {
            aggiornaStato(messaggio);
            sconfitta();
            return;
        }

        aggiornaStato(messaggio);
    }

    private void vittoria() {
        combattimentoFinito = true;
        disabilitaAzioni();

        if (erba != null) {
            game.completaRaccoltaDopoVittoria(erba, nemico);
        } else {
            game.finalizzaCombattimento(nemico);
        }
        player.guadagnaXP(20);

        logLabel.setText(logLabel.getText() + "\n\n✨ Hai sconfitto " + nemico.getNome() + "! Hai ottenuto ingredienti ed esperienza.");
        mostraBottoneChiudi("Continua");
    }

    private void sconfitta() {
        combattimentoFinito = true;
        disabilitaAzioni();

        // La strega non muore: torna alla capanna esausta, ma il mostro resta lì ad attenderla.
        player.setSalute(Math.max(1, player.getSalute()));

        logLabel.setText(logLabel.getText() + "\n\n💀 Sei stata sopraffatta! Torni sui tuoi passi per riprendere le forze.");
        mostraBottoneChiudi("Ritirati");
    }

    private void disabilitaAzioni() {
        azioniPrincipali.setDisable(true);
        azioniMagia.setDisable(true);
    }

    private void mostraBottoneChiudi(String testo) {
        Button chiudi = new Button(testo);
        chiudi.setOnAction(e -> {
            stage.close();
            if (onFine != null) onFine.run();
        });
        root.getChildren().add(chiudi);
    }

    private void aggiornaStato(String messaggio) {
        playerLabel.setText("✨ " + player.getNome() + "   ❤ " + player.getSalute() + "   🔮 " + player.getMana());
        nemicoLabel.setText("👹 " + nemico.getNome() + "   ❤ " + nemico.getSalute());

        playerHpBar.setProgress(Math.min(1.0, Math.max(0, player.getSalute()) / 100.0));
        nemicoHpBar.setProgress(nemicoSaluteMax > 0 ? Math.max(0, nemico.getSalute()) / (double) nemicoSaluteMax : 0);

        logLabel.setText(messaggio);
    }
}
