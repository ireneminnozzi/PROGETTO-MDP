package it.unicam.cs.mpgc.rpg130957.persistence;

import java.io.*;

public class GsonParser {

    public static void write(GameState state, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            GsonProvider.getGson().toJson(state, writer);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Errore durante la serializzazione: " + e.getMessage());
        }
    }

    public static GameState read(String file) {
        try (FileReader reader = new FileReader(file)) {
            return GsonProvider.getGson().fromJson(reader, GameState.class);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Errore durante la lettura (file corrotto?): " + e.getMessage());
        }
        return null;
    }
}