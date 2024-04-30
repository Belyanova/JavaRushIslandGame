package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.enums.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PossibilityOfBeingEatenConfig {
    public static Map<EntityType, Map<EntityType, Integer>> possibilityOfBeingEaten;
    public static int probabilityThreshold;

    public PossibilityOfBeingEatenConfig(ObjectMapper objectMapper, String pathToJson) {
        try {
            File jsonFile = new File(pathToJson);
            List<PossibilityData> possibilityDataList = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, PossibilityData.class));

            // Задание порога вероятности из JSON
            this.probabilityThreshold = possibilityDataList.get(0).percent; // Возьмем порог из первого объекта, предполагая, что все объекты имеют одинаковый порог

            // Преобразование данных из JSON в формат, используемый в конфигурации
            this.possibilityOfBeingEaten = new HashMap<>();
            for (PossibilityData data : possibilityDataList) {
                EntityType attackerType = EntityType.valueOf(data.from.toUpperCase());
                EntityType preyType = EntityType.valueOf(data.to.toUpperCase());

                possibilityOfBeingEaten.putIfAbsent(attackerType, new HashMap<>());
                possibilityOfBeingEaten.get(attackerType).put(preyType, data.percent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PossibilityData {
        String from;
        String to;
        int percent;

        // Геттеры и сеттеры
        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
