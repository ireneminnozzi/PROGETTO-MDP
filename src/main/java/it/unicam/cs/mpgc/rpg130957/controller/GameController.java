package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.crafting.*;
import it.unicam.cs.mpgc.rpg130957.model.inventory.*;
import it.unicam.cs.mpgc.rpg130957.model.magic.*;
import it.unicam.cs.mpgc.rpg130957.model.player.*;
import it.unicam.cs.mpgc.rpg130957.model.items.*;
import it.unicam.cs.mpgc.rpg130957.model.forest.*;
import it.unicam.cs.mpgc.rpg130957.model.quest.*;
import it.unicam.cs.mpgc.rpg130957.model.registry.*;
import it.unicam.cs.mpgc.rpg130957.model.skills.*;
import it.unicam.cs.mpgc.rpg130957.persistence.*;

public class GameController {

    private final Inventario inventario;
    private final ForestMap mappa;
    private final ForestController forestController;
    private final CraftingController craftingController;
    private final QuestManager questManager;
    private final Player player;



    public GameController() {

        this.player = new Player("wiccan");
        this.inventario = new Inventario();
        this.mappa = new ForestMap();
        this.questManager = new QuestManager();

        this.forestController = new ForestController(
                mappa.getStartArea(),
                inventario,
                player,
                questManager
        );


        this.craftingController = new CraftingController(
                inventario,
                new Cauldron(),
                player
        );


        player.equipaggiaArma(ItemRegistry.BASTONE_MAGICO);


    }

    public Player getPlayer() {
        return player;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public ForestArea getPosizione() {
        return forestController.getPosizione();
    }

    public void setPosizione(ForestArea posizione) {
        forestController.setPosizione(posizione);
    }

    public boolean muovi(ForestArea destinazione) {
        return forestController.muovi(destinazione);
    }

    public Item getErbaDisponibile() {
        return forestController.getErbaDisponibile();
    }

    public Enemy trovaGuardiano(Item erba) {
        return forestController.trovaGuardiano(erba);
    }

    public boolean raccogliErbaLibera() {
        return forestController.raccogliErbaLibera();
    }

    public void completaRaccoltaDopoVittoria(Item erba, Enemy nemico) {
        forestController.completaRaccoltaDopoVittoria(erba, nemico);
    }

    public void finalizzaCombattimento(Enemy nemico) {
        forestController.finalizzaCombattimento(nemico);
    }

    public boolean puoiCraftare(Recipe recipe) {
        return craftingController.puoiCraftare(recipe);
    }

    public boolean craft(Recipe recipe) {
        return craftingController.craft(recipe) != null;
    }

    public void assegnaQuest(QuestAvanzata quest) {
        questManager.assegnaQuest(quest);
    }

    public boolean completaQuest() {
        return questManager.completaQuest(inventario, player);
    }

    public Inventario getInventario() {
        return inventario;
    }


    public ForestMap getMappa() {
        return mappa;
    }
}
