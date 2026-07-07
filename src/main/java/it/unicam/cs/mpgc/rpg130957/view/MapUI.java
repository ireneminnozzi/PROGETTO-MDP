package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.combat.Enemy;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * La mappa del bosco. Ogni area mostra al massimo un'erba raccoglibile:
 * se l'erba ha un mostro guardiano ancora vivo, raccoglierla scatena
 * un combattimento a turni con scelte.
 */
public class MapUI {

    private final BorderPane root = new BorderPane();
    private final GameController game;

    public MapUI(GameController game) {
        this.game = game;
        aggiorna();
    }

    public void aggiorna() {
        ForestArea area = game.getPosizione();

        root.setTop(costruisciHud());
        root.setCenter(costruisciScena(area));
        root.setBottom(costruisciNavigazione(area));
        root.setPadding(new Insets(15));
    }

    private HBox costruisciHud() {
        var player = game.getPlayer();
        Label hud = new Label(
                "✨ " + player.getNome() +
                        "   ❤ " + player.getSalute() +
                        "   🔮 " + player.getMana() +
                        "   ⭐ Lv." + player.getLivello()
        );
        hud.setFont(Font.font(15));

        HBox hbox = new HBox(hud);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0, 0, 10, 0));
        return hbox;
    }

    private VBox costruisciScena(ForestArea area) {
        VBox scena = new VBox(15);
        scena.setAlignment(Pos.CENTER);

        Label nomeArea = new Label("🌲 " + area.getNome());
        nomeArea.setFont(Font.font(22));

        StackPane bosco = new StackPane();
        bosco.setPrefSize(500, 260);
        bosco.setStyle("-fx-background-color: linear-gradient(to bottom, #234d20, #16311a); -fx-background-radius: 12;");

        Item erba = game.getErbaDisponibile();

        if (erba != null) {
            Enemy guardiano = game.trovaGuardiano(erba);

            VBox puntoErba = new VBox(6);
            puntoErba.setAlignment(Pos.CENTER);

            Ellipse fiore = new Ellipse(22, 14);
            fiore.setFill(guardiano != null ? Color.web("#c084fc") : Color.web("#7ee787"));

            Label nomeErba = new Label(erba.getNome());
            nomeErba.setStyle("-fx-text-fill: white;");

            puntoErba.getChildren().addAll(fiore, nomeErba);

            if (guardiano != null) {
                Circle mostro = new Circle(10, Color.web("#e03131"));
                Label nomeMostro = new Label("⚠ " + guardiano.getNome());
                nomeMostro.setStyle("-fx-text-fill: #ffb4b4; -fx-font-size: 11px;");
                puntoErba.getChildren().addAll(mostro, nomeMostro);
            }

            Button raccogli = new Button(guardiano != null ? "Raccogli (mostro in agguato!)" : "Raccogli erba");
            raccogli.setOnAction(e -> onRaccogli(erba, guardiano));

            puntoErba.getChildren().add(raccogli);
            bosco.getChildren().add(puntoErba);
        } else {
            Label vuoto = new Label("Non ci sono erbe da raccogliere qui al momento.");
            vuoto.setStyle("-fx-text-fill: #cdbfe8;");
            bosco.getChildren().add(vuoto);
        }

        scena.getChildren().addAll(nomeArea, bosco);
        return scena;
    }

    private void onRaccogli(Item erba, Enemy guardiano) {
        if (guardiano == null) {
            game.raccogliErbaLibera();
            aggiorna();
            return;
        }

        CombatUI.avviaCombattimentoErba(game, guardiano, erba, this::aggiorna);
    }

    private VBox costruisciNavigazione(ForestArea area) {
        VBox nav = new VBox(10);
        nav.setAlignment(Pos.CENTER);
        nav.setPadding(new Insets(10, 0, 0, 0));

        FlowPane collegamenti = new FlowPane(10, 10);
        collegamenti.setAlignment(Pos.CENTER);
        for (ForestArea collegata : area.getCollegamenti()) {
            Button move = new Button("➜ " + collegata.getNome());
            move.setOnAction(e -> {
                game.muovi(collegata);
                aggiorna();
            });
            collegamenti.getChildren().add(move);
        }

        Button tornaCapanna = new Button("🏠 Torna alla capanna");
        tornaCapanna.setOnAction(e -> {
            CapannaUI capanna = new CapannaUI(game);
            Scene scene = new Scene(capanna.getRoot(), 900, 600);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
        });

        nav.getChildren().addAll(collegamenti, tornaCapanna);
        return nav;
    }

    public BorderPane getRoot() {
        return root;
    }
}
