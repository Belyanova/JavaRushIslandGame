package models.services;

import config.EntityCharacteristicsConfig;

public interface IslandAction {

    void refillPlants(int maxCountOfPlantInOneField, EntityCharacteristicsConfig entityCharacteristicsConfig);

    void removeDeathAnimal();
}
