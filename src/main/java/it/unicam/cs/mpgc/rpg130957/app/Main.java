package it.unicam.cs.mpgc.rpg130957.app;

import it.unicam.cs.mpgc.rpg130957.controller.*;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
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



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameController game = new GameController();
        Player player = game.getPlayer();

        // === QUEST DI ESEMPIO ===
        QuestAvanzata quest = new QuestAvanzata(
                "Rituale della Luna",
                List.of(
                        QuestObjective.raccogli(ItemRegistry.FIORE_LUNARE, 3),
                        QuestObjective.sconfiggi(it.unicam.cs.mpgc.rpg130957.model.combat.EnemyType.SPIRITO, 2)
                ),
                50
        );
        game.getQuestManager().assegnaQuest(quest);

        System.out.println("🌙 Benvenuta, " + player.getNome());
        System.out.println("Ti trovi in: " + game.getPosizione().getNome());

        boolean running = true;

        while (running) {

            if (!player.isVivo()) {
                System.out.println("💀 Sei morta... Game Over.");
                break;
            }

            System.out.println("\n=== MENU ===");
            System.out.println("1) Muoviti");
            System.out.println("2) Raccogli risorsa");
            System.out.println("3) Combatti");
            System.out.println("4) Crafta pozione");
            System.out.println("5) Mostra inventario");
            System.out.println("6) Mostra statistiche");
            System.out.println("7) HUD completo");
            System.out.println("8) Quest");
            System.out.println("9) Combatti il Boss");
            System.out.println("10) Lancia una magia");
            System.out.println("11) Avvia la Quest Finale");
            System.out.println("12) Parla con il Druido");
            System.out.println("13) Sblocca Abilità");
            System.out.println("14) Mostra Skill Tree");
            System.out.println("0) Esci");

            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();

            switch (scelta) {

                case 1:
                    System.out.println("Aree disponibili:");
                    for (ForestArea area : game.getPosizione().getCollegamenti()) {
                        System.out.println("- " + area.getNome());
                    }
                    System.out.print("Dove vuoi andare? ");
                    scanner.nextLine();
                    String destinazione = scanner.nextLine();

                    ForestArea target = game.getPosizione().getCollegamenti().stream()
                            .filter(a -> a.getNome().equalsIgnoreCase(destinazione))
                            .findFirst()
                            .orElse(null);

                    if (target != null && game.muovi(target)) {
                        System.out.println("Ti sei spostata in: " + target.getNome());
                    } else {
                        System.out.println("Non puoi andare lì.");
                    }
                    break;

                case 2:
                    if (game.raccogli()) {
                        System.out.println("Hai raccolto una risorsa!");
                    } else {
                        System.out.println("Non hai raccolto nulla.");
                    }
                    break;

                case 3:
                    Weapon arma = ItemRegistry.BASTONE_MAGICO;
                    boolean vinto = game.combatti(arma);
                    System.out.println(vinto ? "Hai vinto il combattimento!" : "Hai perso...");
                    break;

                case 4:
                    System.out.println("=== CRAFTING ===");
                    System.out.println("Per ora non hai ricette collegate al menu.");
                    System.out.println("Aggiungi RecipeRegistry e le colleghiamo subito.");
                    break;

                case 5:
                    System.out.println("Inventario:");
                    game.getInventario().getTutti().forEach((item, qty) ->
                            System.out.println(item.getNome() + ": " + qty));
                    break;

                case 6:
                    System.out.println("=== STATISTICHE ===");
                    System.out.println("Salute: " + player.getSalute());
                    System.out.println("Mana: " + player.getMana());
                    System.out.println("Livello: " + player.getLivello());
                    System.out.println("XP: " + player.getEsperienza());
                    break;

                case 7:
                    PlayerHUD.mostraHUD(player);
                    PlayerHUD.mostraInventario(game.getInventario());
                    break;

                case 8:
                    System.out.println("=== QUEST ===");
                    QuestAvanzata q = game.getQuestManager().getQuestAttiva();
                    if (q == null) {
                        System.out.println("Nessuna quest attiva.");
                        break;
                    }

                    System.out.println("Quest: " + q.getNome());
                    for (QuestObjective o : q.getObiettivi()) {
                        System.out.println("- " + o.getTipo() +
                                " (" + o.getProgresso() + "/" + o.getQuantitaRichiesta() + ")");
                    }

                    if (q.èCompletata()) {
                        System.out.println("La quest è completabile! Vuoi completarla? (s/n)");
                        scanner.nextLine();
                        String risposta = scanner.nextLine();
                        if (risposta.equalsIgnoreCase("s")) {
                            game.completaQuest();
                        }
                    }
                    break;

                case 9:
                    System.out.println("=== BOSS FIGHT ===");
                    Boss boss = new Boss(BossType.DRAGO_PRIMORDIALE);
                    Weapon armaBoss = ItemRegistry.SPADA_DRAGO;

                    while (!boss.isSconfitto() && player.isVivo()) {
                        int dannoGiocatore = armaBoss.getDanno() + player.getLivello() * 3;
                        int dannoBoss = boss.getTipo().getDannoBase();

                        boss.subisciDanno(dannoGiocatore);
                        player.subisciDanno(dannoBoss);

                        System.out.println("Hai inflitto " + dannoGiocatore + " danni al boss. Salute boss: " + boss.getSalute());
                        System.out.println("Il boss ti infligge " + dannoBoss + " danni. Salute tua: " + player.getSalute());
                    }

                    if (boss.isSconfitto()) {
                        System.out.println("✨ Hai sconfitto il Drago Primordiale!");
                        player.guadagnaXP(200);
                        game.getInventario().aggiungiIngrediente(ItemRegistry.ESSENZA_DRAGO, 1);
                    } else {
                        System.out.println("💀 Sei stata sconfitta dal boss...");
                    }
                    break;

                case 10:
                    System.out.println("=== MAGIE ===");

                    if (game.getPosizione().getNemici().isEmpty()) {
                        System.out.println("Non ci sono nemici da colpire.");
                        break;
                    }

                    Enemy enemy = game.getPosizione().getNemici().get(0);

                    System.out.println("Scegli una magia:");
                    System.out.println("1) Fiamma Arcana");
                    System.out.println("2) Lancia Lunare");
                    System.out.println("3) Esplosione Draconica");

                    int sceltaMagia = scanner.nextInt();

                    switch (sceltaMagia) {
                        case 1 -> player.lancia(SpellSet.FIAMMA_ARCANA, enemy);
                        case 2 -> player.lancia(SpellSet.LANCIA_LUNARE, enemy);
                        case 3 -> player.lancia(SpellSet.ESPLOSIONE_DRACONICA, enemy);
                        default -> System.out.println("Scelta non valida.");
                    }

                    if (enemy.isSconfitto()) {
                        System.out.println("Hai ucciso il nemico con la magia!");
                        game.getPosizione().getNemici().remove(enemy);
                        player.guadagnaXP(30);
                    }
                    break;

                case 11:
                    game.assegnaQuestFinale();
                    System.out.println("🌙 La quest finale è iniziata!");
                    break;

                case 12:
                    DialogueRegistry.DRUIDO_INTRO.start();
                    break;

                case 13:
                    System.out.println("Scegli un'abilità:");
                    System.out.println("1) Potenza Arcana");
                    System.out.println("2) Maestria delle Armi");
                    System.out.println("3) Resistenza Mistica");

                    int sceltaSkill = scanner.nextInt();

                    switch (sceltaSkill) {
                        case 1 -> player.getSkillTree().sblocca(SkillSet.POTENZA_ARCANA);
                        case 2 -> player.getSkillTree().sblocca(SkillSet.MAESTRIA_ARMI);
                        case 3 -> player.getSkillTree().sblocca(SkillSet.RESISTENZA_MISTICA);
                        default -> System.out.println("Scelta non valida.");
                    }
                    break;

                case 14:
                    player.getSkillTree().mostra();
                    break;

                case 15:
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
                    break;

                case 16:
                    GameState loaded = LoadManager.carica();
                    if (loaded != null) {

                        // Player
                        player.setSalute(loaded.salute);
                        player.setMana(loaded.mana);
                        player.setLivello(loaded.livello);
                        player.setEsperienza(loaded.esperienza);

                        // Inventario
                        game.getInventario().clear();
                        loaded.inventario.forEach((nome, qty) -> {
                            var item = ItemsRegistry.getByName(nome);
                            if (item != null) {
                                game.getInventario().aggiungiIngrediente(item, qty);
                            }
                        });

                        // Posizione
                        ForestArea nuovaPosizione = ForestRegistry.getByName(loaded.posizione);
                        if (nuovaPosizione != null) {
                            game.setPosizione(nuovaPosizione);
                        }

                        // Abilità
                        loaded.abilitaSbloccate.forEach(skillName -> {
                            var skill = SkillSet.getByName(skillName);
                            if (skill != null) {
                                player.getSkillTree().sblocca(skill);
                            }
                        });

                        // Quest
                        if (loaded.questAttiva != null) {
                            var pursuit = QuestRegistry.getByName(loaded.questAttiva);
                            if (quest != null) {
                                game.getQuestManager().assegnaQuest(quest);
                                loaded.progressoQuest.forEach((obj, prog) ->
                                        quest.setProgresso(obj, prog)
                                );
                            }
                        }

                        // Boss
                        game.setBossSconfitto(loaded.bossSconfitto);

                        // Nemici
                        loaded.nemiciPerArea.forEach((areaName, count) -> {
                            ForestArea area = ForestRegistry.getByName(areaName);
                            if (area != null) {
                                area.setNemici(count);
                            }
                        });

                        // Magie
                        loaded.magieSbloccate.forEach(spellName -> {
                            var spell = SpellRegistry.getByName(spellName);
                            if (spell != null) {
                                player.sbloccaMagia(spell);
                            }
                        });

                        // Ricette
                        loaded.ricetteSbloccate.forEach(recipeName -> {
                            var recipe = RecipeRegistry.getByName(recipeName);
                            if (recipe != null) {
                                player.sbloccaRicetta(recipe);
                            }
                        });

                        System.out.println("✨ Stato di gioco ricostruito!");
                    }
                    break;





                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }

        System.out.println("🌙 Fine del gioco.");
    }
}
