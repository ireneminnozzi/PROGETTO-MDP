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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class MapUI {

    private final BorderPane root = new BorderPane();
    private final StackPane bosco = new StackPane();
    private final ImageView sfondo = new ImageView();
    private final GameController game;

    public MapUI(GameController game) {
        this.game = game;

        bosco.getChildren().add(sfondo);
        bosco.setMinSize(0, 0);
        bosco.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sfondo.setPreserveRatio(false);
        sfondo.setOpacity(0.75);
        sfondo.fitWidthProperty().bind(bosco.widthProperty());
        sfondo.fitHeightProperty().bind(bosco.heightProperty());
        bosco.setStyle("-fx-background-color: #16311a; -fx-background-radius: 12;");

        Image mappaSfondo = SpriteRegistry.getMappaBosco();
        if (mappaSfondo != null) {
            sfondo.setImage(mappaSfondo);
        }

        root.setPadding(new Insets(15));
        root.setCenter(bosco);

        aggiorna();
    }

    public void aggiorna() {
        ForestArea area = game.getPosizione();

        root.setTop(costruisciTesta(area));
        aggiornaContenutoScena(area);
        root.setBottom(costruisciNavigazione(area));
    }

    private VBox costruisciTesta(ForestArea area) {
        Label nomeArea = new Label("🌲 " + area.getNome());
        nomeArea.setFont(Font.font(22));

        VBox testa = new VBox(6, costruisciHud(), nomeArea);
        testa.setAlignment(Pos.CENTER_LEFT);
        testa.setPadding(new Insets(0, 0, 10, 0));
        return testa;
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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Image witchBack = SpriteRegistry.getWitchBack();
        if (witchBack != null) {
            ImageView avatar = new ImageView(witchBack);
            avatar.setFitHeight(48);
            avatar.setPreserveRatio(true);
            hbox.getChildren().add(avatar);
        }

        hbox.getChildren().add(hud);

        Button muto = new Button(MusicManager.getInstance().isMuto() ? "🔇" : "🔊");
        muto.setOnAction(e -> {
            MusicManager.getInstance().toggleMuto();
            muto.setText(MusicManager.getInstance().isMuto() ? "🔇" : "🔊");
        });
        hbox.getChildren().add(muto);

        return hbox;
    }

    /** Aggiorna solo il contenuto in primo piano (erba/mostro), lo sfondo resta al suo posto. */
    private void aggiornaContenutoScena(ForestArea area) {
        // Rimuove tutto tranne lo sfondo (primo figlio).
        bosco.getChildren().retainAll(java.util.List.of(sfondo));

        Item erba = game.getErbaDisponibile();

        if (erba != null) {
            Enemy guardiano = game.trovaGuardiano(erba);

            VBox puntoErba = new VBox(6);
            puntoErba.setAlignment(Pos.CENTER);
            puntoErba.setStyle(
                    "-fx-background-color: rgba(0,0,0,0.45); -fx-background-radius: 14; " +
                            "-fx-padding: 16;"
            );

            Image erbaSprite = SpriteRegistry.getItemImage(erba);
            if (erbaSprite == null) erbaSprite = SpriteRegistry.getIconaErbaGenerica();

            if (erbaSprite != null) {
                ImageView erbaView = new ImageView(erbaSprite);
                erbaView.setFitWidth(64);
                erbaView.setPreserveRatio(true);
                puntoErba.getChildren().add(erbaView);
            } else {
                Ellipse fiore = new Ellipse(22, 14);
                fiore.setFill(guardiano != null ? Color.web("#c084fc") : Color.web("#7ee787"));
                puntoErba.getChildren().add(fiore);
            }

            Label nomeErba = new Label(erba.getNome());
            nomeErba.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
            puntoErba.getChildren().add(nomeErba);

            if (guardiano != null) {
                Image mostroSprite = SpriteRegistry.getEnemyImage(guardiano.getTipo());
                if (mostroSprite != null) {
                    ImageView mostroView = new ImageView(mostroSprite);
                    mostroView.setFitWidth(72);
                    mostroView.setPreserveRatio(true);
                    puntoErba.getChildren().add(mostroView);
                } else {
                    Circle mostro = new Circle(10, Color.web("#e03131"));
                    puntoErba.getChildren().add(mostro);
                }

                Label nomeMostro = new Label("⚠ " + guardiano.getNome());
                nomeMostro.setStyle("-fx-text-fill: #ffb4b4; -fx-font-size: 11px;");
                puntoErba.getChildren().add(nomeMostro);
            }

            Button raccogli = new Button(guardiano != null ? "Raccogli (mostro in agguato!)" : "Raccogli erba");
            raccogli.setOnAction(e -> onRaccogli(erba, guardiano));

            puntoErba.getChildren().add(raccogli);
            bosco.getChildren().add(puntoErba);
        } else {
            Label vuoto = new Label("Non ci sono erbe da raccogliere qui al momento.");
            vuoto.setStyle(
                    "-fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.45); " +
                            "-fx-background-radius: 10; -fx-padding: 10 16;"
            );
            bosco.getChildren().add(vuoto);
        }
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
