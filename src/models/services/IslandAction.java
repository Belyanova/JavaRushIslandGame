package models.services;

import config.EntityCharacteristicsConfig;
import config.IslandConfig;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.services.implementation.islandActionImplementation;

public interface IslandAction {

    void removeAnimalAtCoordinates(Entity entity, Field coordinates);

    void addedAnimalAtCoordinates(Entity entity, Field coordinates);

    void refillPlants(int maxCountOfPlantInOneField, EntityCharacteristicsConfig entityCharacteristicsConfig);

    void removeDeathAnimal();
}
