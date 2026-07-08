package it.unicam.cs.mpgc.rpg130957.view;


import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        root.getChildren().add(new Label("🎒 Inventario"));

        VBox lista = new VBox(8);
        game.getInventario().getTutti().forEach((item, qty) -> {
            HBox riga = new HBox(10);
            riga.setAlignment(Pos.CENTER_LEFT);

            Image icona = SpriteRegistry.getItemImage(item);
            if (icona != null) {
                ImageView iconaView = new ImageView(icona);
                iconaView.setFitHeight(32);
                iconaView.setPreserveRatio(true);
                riga.getChildren().add(iconaView);
            }

            riga.getChildren().add(new Label(item.getNome() + ": " + qty));
            lista.getChildren().add(riga);
        });

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);
        root.getChildren().add(scroll);

        Button chiudi = new Button("Chiudi");
        chiudi.setOnAction(e -> stage.close());
        root.getChildren().add(chiudi);

        stage.setScene(new Scene(root, 400, 350));
        stage.show();
    }
}
