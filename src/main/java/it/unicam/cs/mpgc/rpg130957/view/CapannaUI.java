package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.persistence.GameState;
import it.unicam.cs.mpgc.rpg130957.persistence.SaveManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CapannaUI {

    private final VBox root = new VBox(20);

    public CapannaUI(GameController game) {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #2b2140;");

        Label titolo = new Label("🏠 La tua capanna");
        titolo.setFont(Font.font(22));
        titolo.setStyle("-fx-text-fill: #e6d9ff;");

        Label descrizione = new Label(
                "Ti trovi nella tua capanna, tra libri antichi e il profumo di erbe essiccate.\n" +
                        "Il banco degli intrugli aspetta solo i tuoi ingredienti."
        );
        descrizione.setWrapText(true);
        descrizione.setMaxWidth(600);
        descrizione.setStyle("-fx-text-fill: #cdbfe8;");

        Button pozioni = new Button("🧪 Banco pozioni");
        pozioni.setOnAction(e -> CraftingUI.show(game));

        Button inventario = new Button("🎒 Inventario");
        inventario.setOnAction(e -> InventoryUI.show(game));

        Button salva = new Button("💾 Salva partita");
        salva.setOnAction(e -> {
            salvaPartita(game);
            new Alert(Alert.AlertType.INFORMATION, "Partita salvata!").showAndWait();
        });

        Button esci = new Button("🌲 Esci dalla capanna");
        esci.setOnAction(e -> {
            MapUI map = new MapUI(game);
            Scene scene = new Scene(map.getRoot(), 900, 600);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
        });

        root.getChildren().addAll(titolo, descrizione, pozioni, inventario, salva, esci);
    }

    private static void salvaPartita(GameController game) {
        var player = game.getPlayer();

        Map<String, Integer> invMap = new HashMap<>();
        game.getInventario().getTutti().forEach((item, qty) -> invMap.put(item.getNome(), qty));

        GameState state = new GameState(
                player.getSalute(),
                player.getMana(),
                player.getLivello(),
                player.getEsperienza(),
                invMap,
                game.getPosizione().getNome()
        );

        SaveManager.salva(state);
    }

    public VBox getRoot() {
        return root;
    }
}
