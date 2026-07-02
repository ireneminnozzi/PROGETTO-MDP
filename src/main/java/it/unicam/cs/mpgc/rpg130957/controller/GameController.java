package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.Inventario.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestMap;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Cauldron;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.quest.Quest;
import it.unicam.cs.mpgc.rpg130957.model.quest.QuestManager;

//Coordina tutti i sistemi: bosco, shop, crafting, quest, inventario e wallet.

public class GameController {

    private final Inventario inventario;
    private final Wallet wallet;
    private final ForestMap mappa;
    private final ForestController forestController;
    private final ShopController shopController;
    private final CraftingController craftingController;
    private final QuestManager questManager;

    public GameController() {
        this.inventario = new Inventario();
        this.wallet = new Wallet(50); // oro iniziale
        this.mappa = new ForestMap();
        this.forestController = new ForestController(mappa.getStartArea(), inventario);
        this.shopController = new ShopController(inventario, wallet);
        this.craftingController = new CraftingController(inventario, new Cauldron());
        this.questManager = new QuestManager();
    }

    // BOSCO
    public ForestArea getPosizione() {
        return forestController.getPosizione();
    }

    public boolean muovi(ForestArea destinazione) {
        return forestController.muovi(destinazione);
    }

    public boolean raccogli() {
        return forestController.raccogliRisorsa();
    }

    public boolean combatti(Weapon arma) {
        return forestController.combattiNemico(arma);
    }

    // SHOP
    public boolean compra(Item item, int quantita) {
        return shopController.compra(item, quantita);
    }

    public boolean vendi(Item item, int quantita) {
        return shopController.vendi(item, quantita);
    }

    // CRAFTING
    public boolean puoiCraftare(Recipe recipe) {
        return craftingController.puoiCraftare(recipe);
    }

    public boolean craft(Recipe recipe) {
        return craftingController.craft(recipe) != null;
    }

    // QUEST
    public void assegnaQuest(Quest quest) {
        questManager.assegnaQuest(quest);
    }

    public boolean completaQuest() {
        return questManager.completaQuest(inventario, wallet);
    }

    // INFO
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
