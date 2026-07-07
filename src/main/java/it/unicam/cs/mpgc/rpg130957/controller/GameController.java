package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.controller.*;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.crafting.*;
import it.unicam.cs.mpgc.rpg130957.model.dialogue.*;
import it.unicam.cs.mpgc.rpg130957.model.economy.*;
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
    private final Wallet wallet;
    private final ForestMap mappa;
    private final ForestController forestController;
    private final ShopController shopController;
    private final CraftingController craftingController;
    private final QuestManager questManager;
    private final Player player;
    private ForestArea posizione;
    private boolean bossSconfitto;



    public GameController() {

        this.player = new Player("wiccan");
        this.inventario = new Inventario();
        this.wallet = new Wallet(50);
        this.mappa = new ForestMap();
        this.questManager = new QuestManager();

        this.forestController = new ForestController(
                mappa.getStartArea(),
                inventario,
                player,
                questManager
        );

        this.shopController = new ShopController(inventario, wallet);

        this.craftingController = new CraftingController(
                inventario,
                new Cauldron(),
                player
        );
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

    public void setPosizione(ForestArea posizione) { this.posizione = posizione;}

    public boolean muovi(ForestArea destinazione) {
        return forestController.muovi(destinazione);
    }

    public boolean raccogli() {
        return forestController.raccogliRisorsa();
    }

    public boolean combatti(Weapon arma) {
        return forestController.combattiNemico(arma);
    }

    public boolean compra(Item item, int quantita) {
        return shopController.compra(item, quantita);
    }

    public boolean vendi(Item item, int quantita) {
        return shopController.vendi(item, quantita);
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
        return questManager.completaQuest(inventario, wallet, player);
    }

    public void assegnaQuestFinale() {
        questManager.assegnaQuest(QuestFinale.creaQuestFinale());
    }

    public void setBossSconfitto(boolean sconfitto) {
        this.bossSconfitto = sconfitto;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public ForestMap getMappa() {
        return mappa;
    }
}
