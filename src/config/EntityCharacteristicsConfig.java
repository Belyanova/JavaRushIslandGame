package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.abstracts.Entity;
import models.enums.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static models.constants.Constants.PATH_TO_ENTITY_CHARACTERISTICS;

public class EntityCharacteristicsConfig {
    private Map<EntityType, Entity> entityMapConfig;

    public Map<EntityType, Entity> getEntityMapConfig() {
        return entityMapConfig;
    }

    public EntityCharacteristicsConfig(ObjectMapper objectMapper, String pathToJson) {
        this.entityMapConfig = new HashMap<>();

        try {
            // Чтение JSON из файла и преобразование в объект Java
            File jsonFile = new File(PATH_TO_ENTITY_CHARACTERISTICS);
            EntityCharacteristicsJsonData entityCharacteristicsJsonData = objectMapper.readValue(jsonFile, EntityCharacteristicsJsonData.class);

            // Заполнение entityMapConfig сущностями
            for (Map.Entry<String, EntityCharacteristicsJsonData.EntityData> entry : entityCharacteristicsJsonData.getEntities().entrySet()) {
                String entityName = entry.getKey();
                EntityCharacteristicsJsonData.EntityData entityData = entry.getValue();

                EntityType entityType = EntityType.valueOf(entityName.toUpperCase());
                Entity entity = new Entity(entityData.getWeight(), entityData.getMaxCountOnField(), entityData.getSpeed(), entityData.getKilogramsOfFoodToFillYouUp());

                entityMapConfig.put(entityType, entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
