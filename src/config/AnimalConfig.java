package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AnimalConfig {
    private int percentsToRemove;

    public AnimalConfig(String pathToSettingsFile) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToSettingsFile))){
            Properties properties =new Properties();
            properties.load(bufferedReader);
            this.percentsToRemove = Integer.parseInt(properties.getProperty("animals.percentsToRemove"));
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPercentsToRemove() {
        return percentsToRemove;
    }
}
