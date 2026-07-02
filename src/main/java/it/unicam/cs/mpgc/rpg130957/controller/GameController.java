package it.unicam.cs.mpgc.rpg130957.controller;

import it.unicam.cs.mpgc.rpg130957.model.inventory.Inventario;
import it.unicam.cs.mpgc.rpg130957.model.economy.Wallet;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestMap;
import it.unicam.cs.mpgc.rpg130957.model.forest.ForestArea;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Cauldron;
import it.unicam.cs.mpgc.rpg130957.model.items.Item;
import it.unicam.cs.mpgc.rpg130957.model.crafting.Recipe;
import it.unicam.cs.mpgc.rpg130957.model.items.Weapon;
import it.unicam.cs.mpgc.rpg130957.model.player.Player;
import it.unicam.cs.mpgc.rpg130957.model.quest.Quest;
import it.unicam.cs.mpgc.rpg130957.model.quest.QuestManager;

public class GameController {

    private final Inventario inventario;
    private final Wallet wallet;
    private final ForestMap mappa;
    private final ForestController forestController;
    private final ShopController shopController;
    private final CraftingController craftingController;
    private final QuestManager questManager;
    private final Player player;

    public GameController() {
        this.player = new Player("wiccan");
        this.inventario = new Inventario();
        this.wallet = new Wallet(50);
        this.mappa = new ForestMap();
        this.forestController = new ForestController(mappa.getStartArea(), inventario, player);
        this.shopController = new ShopController(inventario, wallet);
        this.craftingController = new CraftingController(inventario, new Cauldron(), player);
        this.questManager = new QuestManager();
    }

    public Player getPlayer() {
        return player;
    }

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

    public void assegnaQuest(Quest quest) {
        questManager.assegnaQuest(quest);
    }

    public boolean completaQuest() {
        return questManager.completaQuest(inventario, wallet);
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
