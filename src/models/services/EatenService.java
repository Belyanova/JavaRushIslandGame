package models.services;

import models.services.implementation.islandActionImplementation;
import models.abstracts.Entity;
import models.island.Field;

import java.util.Random;

public interface EatenService {
    void eating(Entity attacker, Field field, islandActionImplementation islandActionImplementation, Random random);
}
