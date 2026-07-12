package it.unicam.cs.mpgc.rpg130957.app;

import it.unicam.cs.mpgc.rpg130957.controller.*;
import it.unicam.cs.mpgc.rpg130957.model.combat.*;
import it.unicam.cs.mpgc.rpg130957.model.dialogue.*;
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
import java.util.Map;
import java.util.Scanner;

// questo menu in realtà non viene utilizzato

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameController game = new GameController();
        Player player = game.getPlayer();


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
            System.out.println("9) Lancia una magia");
            System.out.println("10) Parla con il Druido");
            System.out.println("11) Sblocca Abilità");
            System.out.println("12) Mostra Skill Tree");
            System.out.println("13) Salva");
            System.out.println("14) Carica");
            System.out.println("0) Esci");

            System.out.print("Scelta: ");
            if (!scanner.hasNextInt()) {
                System.out.println("⚠️ Inserisci un numero.");
                scanner.nextLine();
                continue;
            }
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {

                case 1:
                    System.out.println("Aree disponibili:");
                    for (ForestArea area : game.getPosizione().getCollegamenti()) {
                        System.out.println("- " + area.getNome());
                    }
                    System.out.print("Dove vuoi andare? ");
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
                        String risposta = scanner.nextLine();
                        if (risposta.equalsIgnoreCase("s")) {
                            game.completaQuest();
                        }
                    }
                    break;

                case 9:
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

                    if (!scanner.hasNextInt()) {
                        System.out.println("Scelta non valida.");
                        scanner.nextLine();
                        break;
                    }
                    int sceltaMagia = scanner.nextInt();
                    scanner.nextLine();

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


                case 10:
                    DialogueRegistry.DRUIDO_INTRO.start();
                    break;

                case 11:
                    System.out.println("Scegli un'abilità:");
                    System.out.println("1) Potenza Arcana");
                    System.out.println("2) Maestria delle Armi");
                    System.out.println("3) Resistenza Mistica");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Scelta non valida.");
                        scanner.nextLine();
                        break;
                    }
                    int sceltaSkill = scanner.nextInt();
                    scanner.nextLine();

                    switch (sceltaSkill) {
                        case 1 -> player.getSkillTree().sblocca(SkillSet.POTENZA_ARCANA);
                        case 2 -> player.getSkillTree().sblocca(SkillSet.MAESTRIA_ARMI);
                        case 3 -> player.getSkillTree().sblocca(SkillSet.RESISTENZA_MISTICA);
                        default -> System.out.println("Scelta non valida.");
                    }
                    break;

                case 12:
                    player.getSkillTree().mostra();
                    break;

                case 13:
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

                case 14:
                    GameState loaded = LoadManager.carica();
                    if (loaded != null) {

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
                            var pursuit = QuestRegistry.getByName(loaded.questAttiva);
                            if (pursuit != null) {
                                game.getQuestManager().assegnaQuest(pursuit);
                                loaded.progressoQuest.forEach((obj, prog) ->
                                        pursuit.setProgresso(obj, prog)
                                );
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