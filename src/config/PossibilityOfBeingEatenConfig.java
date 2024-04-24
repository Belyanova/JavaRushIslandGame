package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.abstracts.Entity;

import java.util.Map;

public class PossibilityOfBeingEatenConfig {
    private Map<Map<Entity, Entity>, Long> possibilityOfBeingEaten;

    public PossibilityOfBeingEatenConfig(ObjectMapper objectMapper, String pathToJson) {
        this.possibilityOfBeingEaten = possibilityOfBeingEaten;
    }
}
