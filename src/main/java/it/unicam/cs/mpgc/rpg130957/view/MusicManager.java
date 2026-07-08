package it.unicam.cs.mpgc.rpg130957.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public final class MusicManager {

    private static final MusicManager INSTANCE = new MusicManager();

    private MediaPlayer player;
    private String traccaCorrente;
    private double volume = 0.5;
    private boolean muto = false;

    private MusicManager() {
    }

    public static MusicManager getInstance() {
        return INSTANCE;
    }

    /** Avvia una traccia in loop infinito. Se è già la traccia in riproduzione, non fa nulla. */
    public void playLoop(String nomeFile) {
        if (nomeFile.equals(traccaCorrente) && player != null) {
            return;
        }

        URL risorsa = MusicManager.class.getResource("/audio/audioGioco.mp3");
        if (risorsa == null) {
            System.out.println("⚠ Musica non trovata: /audio/" + nomeFile +
                    " (metti il file in src/main/resources/audio/)");
            return;
        }

        stop();

        Media media = new Media(risorsa.toExternalForm());
        player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(muto ? 0 : volume);
        player.play();
        traccaCorrente = nomeFile;
    }

    public void stop() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
            traccaCorrente = null;
        }
    }

    public void setVolume(double volume) {
        this.volume = Math.max(0, Math.min(1, volume));
        if (player != null && !muto) {
            player.setVolume(this.volume);
        }
    }

    public void toggleMuto() {
        muto = !muto;
        if (player != null) {
            player.setVolume(muto ? 0 : volume);
        }
    }

    public boolean isMuto() {
        return muto;
    }

    /** Dissolvenza breve, utile prima di cambiare traccia (es. entrando in combattimento). */
    public void fermaConDissolvenza(Duration durata) {
        if (player == null) return;
        MediaPlayer corrente = player;
        var timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(Duration.ZERO,
                        new javafx.animation.KeyValue(corrente.volumeProperty(), corrente.getVolume())),
                new javafx.animation.KeyFrame(durata,
                        new javafx.animation.KeyValue(corrente.volumeProperty(), 0))
        );
        timeline.setOnFinished(e -> stop());
        timeline.play();
    }
}
