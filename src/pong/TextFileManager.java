package pong;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Charles on 5/21/2015.
 */
public class TextFileManager {

    private static final int THREAD_COUNT = 4;

    public static TextFileManager textFileManager;

    private final ExecutorService textPool;

    private Map<String, String> map;

    private TextFileManager() {
        this.textPool = Executors.newFixedThreadPool(THREAD_COUNT);
        this.map = new HashMap<>();
    }

    public static TextFileManager getInstance() {
        if (textFileManager == null) {
            textFileManager = new TextFileManager();
        }
        return textFileManager;
    }

    /**
     * Return the id of the added file with the given path
     * @param id the id of the given file
     * @param path the relative path to the file
     * @return the id of the added file with the given path
     */
    public String loadFile(String id, String path) {
        if(map.containsValue(id)) {
            return map.get(id);
        } else {
            map.put(id, path);
            return path;
        }
    }

    /**
     * Writes the string of text to the file with the given id
     * @param id
     * @param text
     */
    public void writeLineToFile(String id, String text) {
        System.out.println("Writing to " + map.get(id));
        if (map.containsKey(id)) {
            File file = new File(map.get(id));

            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                fw.write(text + "\n");
                fw.close();
            } catch (IOException e) {

            }
        }
    }

    public void shutdown() {
        textPool.shutdown();
    }

}
