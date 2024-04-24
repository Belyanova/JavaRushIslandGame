package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.services.EntityJsonData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntityCharacteristicsConfig {
    /*private Map<EntityType, Entity> entityMapConfig;

    public Map<EntityType, Entity> getEntityMapConfig() {
        return entityMapConfig;
    }

    public EntityCharacteristicsConfig(ObjectMapper objectMapper, String pathToJson) {
        //код который парсит json конфиг нужно написать
        HashMap<EntityType, Entity> entityMapConfig = new HashMap<>();

        entityMapConfig.put(EntityType.GRASS, new Entity(10.0, 12, 12, 34.0));
        entityMapConfig.put(EntityType.WOLF, new Entity(10.0, 12, 12, 34.0));
        this.entityMapConfig = entityMapConfig;
    }*/

    private Map<EntityType, Entity> entityMapConfig;

    public Map<EntityType, Entity> getEntityMapConfig() {
        return entityMapConfig;
    }

    public EntityCharacteristicsConfig(ObjectMapper objectMapper, String pathToJson) {
        this.entityMapConfig = new HashMap<>();

        try {
            // Чтение JSON из файла и преобразование в объект Java
            File jsonFile = new File("resources/entity_characteristics.json");
            EntityJsonData entityJsonData = objectMapper.readValue(jsonFile, EntityJsonData.class);

            // Заполнение entityMapConfig сущностями
            for (Map.Entry<String, EntityJsonData.EntityData> entry : entityJsonData.getEntities().entrySet()) {
                String entityName = entry.getKey();
                EntityJsonData.EntityData entityData = entry.getValue();

                EntityType entityType = EntityType.valueOf(entityName.toUpperCase());
                Entity entity = new Entity(entityData.getWeight(), entityData.getMaxCountOnField(), entityData.getSpeed(), entityData.getKilogramsOfFoodToFillYouUp());

                entityMapConfig.put(entityType, entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
