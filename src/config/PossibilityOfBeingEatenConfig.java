package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.enums.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PossibilityOfBeingEatenConfig {
    public static Map<EntityType, Map<EntityType, Integer>> possibilityOfBeingEaten;
    public static int probabilityThreshold;

    public PossibilityOfBeingEatenConfig(ObjectMapper objectMapper, String pathToJson) {
        try {
            File jsonFile = new File(pathToJson);
            Map<String, Object> configData = objectMapper.readValue(jsonFile, Map.class);

            // Задание порога вероятности из JSON
            this.probabilityThreshold = (int) configData.get("probabilityThreshold");

            // Преобразование данных из JSON в формат, используемый в конфигурации
            this.possibilityOfBeingEaten = new HashMap<>();
            Map<String, Map<String, Integer>> possibilityData = (Map<String, Map<String, Integer>>) configData.get("possibilityOfBeingEaten");
            for (Map.Entry<String, Map<String, Integer>> entry : possibilityData.entrySet()) {
                EntityType attackerType = EntityType.valueOf(entry.getKey().toUpperCase());
                Map<EntityType, Integer> innerMap = new HashMap<>();
                for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                    EntityType preyType = EntityType.valueOf(innerEntry.getKey().toUpperCase());
                    innerMap.put(preyType, innerEntry.getValue());
                }
                possibilityOfBeingEaten.put(attackerType, innerMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
