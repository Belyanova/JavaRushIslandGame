package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IslandConfig {
    private int width;
    private int height;
    private int days;

    public IslandConfig(String pathToSettingsFile) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToSettingsFile))){
            Properties properties =new Properties();
            properties.load(bufferedReader);
            this.days = Integer.parseInt(properties.getProperty("island.days"));
            this.width = Integer.parseInt(properties.getProperty("island.width"));
            this.height = Integer.parseInt(properties.getProperty("island.height"));
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDays() {
        return days;
    }
}
