package it.unicam.cs.mpgc.rpg130957.view;

import it.unicam.cs.mpgc.rpg130957.controller.GameController;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.registry.ForestRegistry;
import it.unicam.cs.mpgc.rpg130957.model.registry.ItemsRegistry;
import it.unicam.cs.mpgc.rpg130957.model.registry.QuestRegistry;
import it.unicam.cs.mpgc.rpg130957.model.registry.SpellRegistry;
import it.unicam.cs.mpgc.rpg130957.model.registry.RecipeRegistry;
import it.unicam.cs.mpgc.rpg130957.model.skills.SkillSet;
import it.unicam.cs.mpgc.rpg130957.model.magic.SpellSet;
import it.unicam.cs.mpgc.rpg130957.persistence.GameState;
import it.unicam.cs.mpgc.rpg130957.persistence.LoadManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MenuUI {

    private final VBox root = new VBox(16);

    public MenuUI(GameController game) {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #1b1330;");

        Label titolo = new Label("🌙 Menu Principale");
        titolo.setFont(Font.font(24));
        titolo.setStyle("-fx-text-fill: #e6d9ff;");

        Button nuovaPartita = new Button("Nuova Partita");
        nuovaPartita.setOnAction(e -> vaiInCapanna(game));

        Button caricaPartita = new Button("Carica Partita");
        caricaPartita.setOnAction(e -> {
            boolean caricata = carica(game);
            if (caricata) {
                vaiInCapanna(game);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Nessun salvataggio trovato. Inizia una nuova partita.");
                alert.showAndWait();
            }
        });

        Button esci = new Button("Esci dal gioco");
        esci.setOnAction(e -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });

        root.getChildren().addAll(titolo, nuovaPartita, caricaPartita, esci);
    }

    private void vaiInCapanna(GameController game) {
        CapannaUI capanna = new CapannaUI(game);
        Scene scene = new Scene(capanna.getRoot(), 900, 600);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }

    private boolean carica(GameController game) {
        GameState loaded = LoadManager.carica();
        if (loaded == null) return false;

        var player = game.getPlayer();
        player.setSalute(loaded.salute);
        player.setMana(loaded.mana);
        player.setLivello(loaded.livello);
        player.setEsperienza(loaded.esperienza);

        game.getInventario().clear();
        loaded.inventario.forEach((nome, qty) -> {
            var item = ItemsRegistry.getByName(nome);
            if (item != null) {
                game.getInventario().aggiungiIngrediente(item, qty);
            }
        });

        ForestArea nuovaPosizione = ForestRegistry.getByName(loaded.posizione);
        if (nuovaPosizione != null) {
            game.setPosizione(nuovaPosizione);
        }

        loaded.abilitaSbloccate.forEach(skillName -> {
            var skill = SkillSet.getByName(skillName);
            if (skill != null) {
                player.getSkillTree().sblocca(skill);
            }
        });

        if (loaded.questAttiva != null) {
            var quest = QuestRegistry.getByName(loaded.questAttiva);
            if (quest != null) {
                game.getQuestManager().assegnaQuest(quest);
                loaded.progressoQuest.forEach(quest::setProgresso);
            }
        }


        loaded.nemiciPerArea.forEach((areaName, count) -> {
            ForestArea area = ForestRegistry.getByName(areaName);
            if (area != null) {
                area.setNemici(count);
            }
        });

        loaded.magieSbloccate.forEach(spellName -> {
            var spell = SpellRegistry.getByName(spellName);
            if (spell != null) {
                player.sbloccaMagia(spell);
            }
        });

        loaded.ricetteSbloccate.forEach(recipeName -> {
            var recipe = RecipeRegistry.getByName(recipeName);
            if (recipe != null) {
                player.sbloccaRicetta(recipe);
            }
        });

        return true;
    }

    public VBox getRoot() {
        return root;
    }
}
