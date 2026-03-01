package com.harel.Utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class ConfigReader {

    private static final Config config;

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.json")) {
            if (in == null)
                throw new RuntimeException("config.json not found on classpath");
            config = new Gson().fromJson(new InputStreamReader(in), Config.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

    public static Config get() {
        return config;
    }

    public static String get(String key) {
        try {
            String[] parts = key.split("\\.", 2);
            Field field = Config.class.getDeclaredField(parts[0]);
            Object value = field.get(config);
            if (parts.length == 1)
                return String.valueOf(value);
            Field nestedField = value.getClass().getDeclaredField(parts[1]);
            return String.valueOf(nestedField.get(value));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("No config key found: " + key);
        }
    }

    public static class Config {
        public int implicitWait;
        public int pageLoadTimeout;
        public boolean headless;
        public String URL;
        public Locator firstTimePurchase;
        public Locator direcrion;
        public Locator nextToPickDates;
        public Locator startDateInput;
        public Locator totalDays;
        public Locator moveToPassDetails;


    }

    public static class Locator {
        public String by;
        public String element;
    }
}
