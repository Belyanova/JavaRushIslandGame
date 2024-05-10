package models.services.implementation;

import config.IslandConfig;
import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.DirectionType;
import models.enums.EntityType;
import models.island.Field;
import models.services.MoveService;

import java.util.List;

public class MoveServiceImplementation implements MoveService {
    private final models.services.implementation.islandActionImplementation islandActionImplementation;

    public MoveServiceImplementation(
            models.services.implementation.islandActionImplementation islandActionImplementation) {
        this.islandActionImplementation = islandActionImplementation;
    }

    @Override
    public void move(Animal animal, Field fields, DirectionType directionType, int speed, IslandConfig islandConfig,
                     models.services.implementation.islandActionImplementation islandActionImplementation) {
        Field newField = getFieldByDirection(fields, directionType);
        List<Entity> entitiesInNewField = islandActionImplementation.getIsland().get(newField);
        if (entitiesInNewField != null && !animal.isMoved()){
            islandActionImplementation.removeAnimalAtCoordinates(animal, fields);
            islandActionImplementation.addedAnimalAtCoordinates(animal, newField);
            animal.decreaseHealthPercentage(20);
            System.out.println(
                    EntityType.ofClass(animal.getClass())+ " moved from (" + fields.getX() + "." + fields.getY()
                            + ") to (" + newField.getX() + "." + newField.getY() + ") " + "health percentage: "
                            + animal.getHealthPercentage());
            animal.setIsMoved(true);
        }
    }

    private Field getFieldByDirection(Field current, DirectionType direction) {
        int newX = current.getX();
        int newY = current.getY();

        switch (direction) {
            case UP:
                newY -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
        }

        return new Field(newX, newY);
    }

}
