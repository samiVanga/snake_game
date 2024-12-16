package com.example.snakegame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * MusicPlayer class is used to play the music in the game
 * @author Samiksha Vanga
 */
public class MusicPlayer {
    private String filename;
    private MediaPlayer mediaPlayer;
    /**
     * Constructs a new MusicPlayer with the specified music file.
     *
     * @param filename The name of the music file.
     */

    public MusicPlayer(String filename) {
        this.filename = filename;
    }
    /**
     * Plays the specified music file.
     */


    public void play() {
        try {
            // Use getResource to load the music file
            String resourcePath = "/" + filename;
            String uriString = getClass().getResource(resourcePath).toExternalForm();

            Media media = new Media(uriString);
            // Create a MediaPlayer
            mediaPlayer = new MediaPlayer(media);
            // Play the media
            mediaPlayer.play();
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Stops the currently playing music.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
