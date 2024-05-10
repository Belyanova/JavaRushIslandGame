package models.services.implementation;

import config.EntityCharacteristicsConfig;
import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.services.MultiplyService;
import java.util.List;

import static models.services.implementation.islandActionImplementation.getMaxCountOnField;

public class MultiplyServiceImplementation implements MultiplyService {

    private final models.services.implementation.islandActionImplementation islandActionImplementation;

    public MultiplyServiceImplementation(
            models.services.implementation.islandActionImplementation islandActionImplementation) {
        this.islandActionImplementation = islandActionImplementation;
    }

    @Override
    /*public void multiply(
            Animal animal, Field fields,
            models.services.implementation.islandActionImplementation islandActionImplementation) {
        List<Entity> entitiesInField = islandActionImplementation.getIsland().get(fields);
        if (entitiesInField == null) {
            System.out.println("Reproduction failed");
            return;
        }

        int animalCount = 0;
        for (Entity entity : entitiesInField) {
            if (entity instanceof Animal && entity != animal) {
                animalCount++;
                if (animalCount > 1) {
                    islandActionImplementation.addedAnimalAtCoordinates(animal, fields);
                    System.out.println(
                            "Reproduction was successful. It was added " + EntityType.ofClass(animal.getClass())
                                    + " in (" + fields.getX() + "." + fields.getY() + ")");
                    return;
                }
            }
        }
        animal.decreaseHealthPercentage(20);
    }*/

    public void multiply(
            Animal animal, Field fields,
            models.services.implementation.islandActionImplementation islandActionImplementation,
            EntityCharacteristicsConfig entityCharacteristicsConfig) {
        List<Entity> entitiesInField = islandActionImplementation.getIsland().get(fields);
        if (entitiesInField == null) {
            System.out.println("Reproduction failed");
            return;
        }

        EntityType animalType = EntityType.ofClass(animal.getClass());
        int maxCount = getMaxCountOnField(entityCharacteristicsConfig, animalType);

        int animalCount = 0;
        for (Entity entity : entitiesInField) {
            if (entity instanceof Animal && entity != animal && EntityType.ofClass(entity.getClass()) == animalType) {
                animalCount++;
                if (animalCount >=  maxCount) {
                    System.out.println(
                            "Reproduction failed. Maximum number of " + animalType + " reached in this field");
                    return;
                }
            }
        }

        islandActionImplementation.addedAnimalAtCoordinates(animal, fields);
        System.out.println("Reproduction was successful. It was added " + animalType +
                " in (" + fields.getX() + "." + fields.getY() + ")");
    }

}
