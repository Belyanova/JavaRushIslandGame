package models.services.implementation;

import config.EntityCharacteristicsConfig;
import models.enums.EntityType;
import models.services.AnimalAction;

public class AnimalActionImplementation implements AnimalAction {

    @Override
    public Integer getSpeed(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getSpeed();
    }
}
