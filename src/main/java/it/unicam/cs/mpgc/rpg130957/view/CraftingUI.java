package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import it.unicam.cs.mpgc.rpg130957.model.inventory.RecipeBook;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Il banco pozioni della capanna: mostra le ricette conosciute, gli ingredienti
 * richiesti e permette di craftare una pozione se si possiedono gli ingredienti.
 */
public class CraftingUI {

    public static void show(GameController game) {
        Stage stage = new Stage();
        stage.setTitle("🧪 Banco delle pozioni");

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));

        Label titolo = new Label("🧪 Banco delle pozioni");
        root.getChildren().add(titolo);

        VBox listaRicette = new VBox(10);
        root.getChildren().add(listaRicette);

        Runnable[] aggiorna = new Runnable[1];
        aggiorna[0] = () -> {
            listaRicette.getChildren().clear();
            for (Recipe recipe : RecipeBook.getAllRecipes()) {
                listaRicette.getChildren().add(rigaRicetta(game, recipe, aggiorna[0]));
            }
        };
        aggiorna[0].run();

        ScrollPane scroll = new ScrollPane(listaRicette);
        scroll.setFitToWidth(true);

        Button chiudi = new Button("Chiudi");
        chiudi.setOnAction(e -> stage.close());

        VBox contenitore = new VBox(12, titolo, scroll, chiudi);
        contenitore.setPadding(new Insets(20));

        stage.setScene(new Scene(contenitore, 480, 420));
        stage.show();
    }

    private static HBox rigaRicetta(GameController game, Recipe recipe, Runnable aggiorna) {
        StringBuilder ingredientiTesto = new StringBuilder();
        recipe.getIngredienti().forEach((ingrediente, quantita) -> {
            int posseduti = game.getInventario().getQuantita(ingrediente);
            ingredientiTesto.append(ingrediente.getNome())
                    .append(" (").append(posseduti).append("/").append(quantita).append(")  ");
        });

        Label descrizione = new Label(
                recipe.getRisultato().getNome() + "\n" + ingredientiTesto
        );
        descrizione.setWrapText(true);
        descrizione.setMaxWidth(300);

        Button crafta = new Button("Crea");
        boolean puoiCraftare = game.puoiCraftare(recipe);
        crafta.setDisable(!puoiCraftare);
        crafta.setOnAction(e -> {
            boolean creato = game.craft(recipe);
            Alert alert = new Alert(creato ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING,
                    creato ? "Hai creato: " + recipe.getRisultato().getNome() + "!"
                            : "Ingredienti insufficienti.");
            alert.showAndWait();
            aggiorna.run();
        });

        HBox riga = new HBox(15, descrizione, crafta);
        return riga;
    }
}
