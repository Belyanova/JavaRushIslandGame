package models.services;

import config.EntityCharacteristicsConfig;
import config.IslandConfig;
import models.services.implementation.islandActionImplementation;
import models.abstracts.Animal;
import models.enums.DirectionType;
import models.island.Field;

public interface MoveService {
    void move(
            Animal entity, Field fieldFrom, DirectionType directionType, int speed, IslandConfig islandConfig,
            islandActionImplementation islandActionImplementation,
            EntityCharacteristicsConfig entityCharacteristicsConfig);
}
