package models.services;

import config.EntityCharacteristicsConfig;
import models.enums.EntityType;

public interface AnimalAction {
    Integer getSpeed(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType);
}
