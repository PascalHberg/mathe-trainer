package com.mathetrainer.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Manages high score persistence with JSON storage.
 * Optimized for minimal I/O operations.
 */
public class StatsManager {
    
    private static final String DATA_DIR = "data";
    private static final String HIGHSCORE_FILE = DATA_DIR + File.separator + "highscore.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private static int cachedHighscore = -1;
    
    static {
        new File(DATA_DIR).mkdirs();
    }
    
    /**
     * Load highscore from file with caching
     * @return Current highscore
     */
    public static int loadHighscore() {
        if (cachedHighscore != -1) {
            return cachedHighscore;
        }
        
        try {
            File file = new File(HIGHSCORE_FILE);
            if (!file.exists()) {
                cachedHighscore = 0;
                return 0;
            }
            
            try (FileReader reader = new FileReader(file)) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                cachedHighscore = json.has("highscore") ? json.get("highscore").getAsInt() : 0;
                return cachedHighscore;
            }
        } catch (IOException e) {
            cachedHighscore = 0;
            return 0;
        }
    }
    
    /**
     * Save highscore to file
     * @param score Score to save
     */
    public static void saveHighscore(int score) {
        cachedHighscore = score;
        
        try {
            new File(DATA_DIR).mkdirs();
            JsonObject json = new JsonObject();
            json.addProperty("highscore", score);
            
            try (FileWriter writer = new FileWriter(HIGHSCORE_FILE)) {
                GSON.toJson(json, writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to save highscore: " + e.getMessage());
        }
    }
    
    /**
     * Get cached highscore without file I/O
     * @return Cached highscore
     */
    public static int getCachedHighscore() {
        return Math.max(0, cachedHighscore);
    }
}
