package models.services;

import config.EntityCharacteristicsConfig;
import models.abstracts.Animal;
import models.island.Field;

public interface MultiplyService {
    void multiply(
            Animal animal, Field fields,
            models.services.implementation.islandActionImplementation islandActionImplementation,
            EntityCharacteristicsConfig entityCharacteristicsConfig);
}
