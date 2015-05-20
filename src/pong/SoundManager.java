package pong;

import javafx.scene.media.AudioClip;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Charles on 5/19/2015.
 */
public class SoundManager {

    // Singleton instance of the class
    private static SoundManager soundManager;

    // Pool thread for playing the sounds
    private final ExecutorService soundPool;

    // Map for storing resource addresses
    Map<String, AudioClip> map = new HashMap<>();

    private SoundManager(int threadCount) {
        this.soundPool = Executors.newFixedThreadPool(threadCount);
    }

    public static SoundManager getInstance(int threadCount) {
        if (soundManager == null) {
            soundManager = new SoundManager(threadCount);
        }
        return soundManager;
    }

    public static SoundManager getInstance() {
        return soundManager;
    }

    public void loadSound(String id, URL url) throws FileNotFoundException {
        if (url == null) {
            throw new FileNotFoundException("This file cannot be found.");
        }
        AudioClip sound = new AudioClip(url.toExternalForm());
        map.put(id, sound);
    }

    public void playSound(final String id) {
        Runnable playSoundRunnable = (
                () -> map.get(id).play()
                );
        soundPool.execute(playSoundRunnable);
    }

    public void shutdown() {
        soundPool.shutdown();
    }
}
