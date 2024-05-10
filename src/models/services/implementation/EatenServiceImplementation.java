package models.services.implementation;

import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.herbivores.Herbivorous;
import models.island.Field;
import models.plans.Grass;
import models.predators.Predator;
import models.services.EatenService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static config.PossibilityOfBeingEatenConfig.possibilityOfBeingEaten;
import static config.PossibilityOfBeingEatenConfig.probabilityThreshold;

public class EatenServiceImplementation implements EatenService {

    private final models.services.implementation.islandActionImplementation islandActionImplementation;

    public EatenServiceImplementation(models.services.implementation.islandActionImplementation islandActionImplementation) {
        this.islandActionImplementation = islandActionImplementation;
    }

    public boolean isEaten(EntityType attackerType, EntityType preyType) {
        Map<EntityType, Integer> attackerProbabilities = possibilityOfBeingEaten.get(attackerType);
        if (attackerProbabilities != null) {
            Integer probability = attackerProbabilities.get(preyType);
            if (probability != null) {
                return probability > probabilityThreshold;
            }
        }
        return false;
    }

    public void eating(Entity attacker, Field field, models.services.implementation.islandActionImplementation islandActionImplementation, Random random) {
        List<Entity> entitiesInNewField = islandActionImplementation.getIsland().get(field);
        EntityType attackerType = EntityType.ofClass(attacker.getClass());

        if (attacker instanceof Predator) {
            Entity randomEntity = entitiesInNewField.get(random.nextInt(entitiesInNewField.size()));
            EntityType preyType = EntityType.ofClass(randomEntity.getClass());

            if (isEaten(attackerType, preyType)) {
                islandActionImplementation.removeAnimalAtCoordinates(randomEntity, field);
                ((Animal) attacker).increaseHealthPercentage(20);
                System.out.println(attackerType + " was eaten by " + preyType
                        + " from (" + field.getX() + "." + field.getY() + ") " + "health percentage: "
                        + ((Animal) attacker).getHealthPercentage());
            }
        } else if (attacker instanceof Herbivorous) {
            for (Iterator<Entity> iterator = entitiesInNewField.iterator(); iterator.hasNext();) {
                Entity entity = iterator.next();
                if (entity instanceof Grass) {
                    iterator.remove();
                    ((Animal) attacker).increaseHealthPercentage(20);
                    System.out.println(attackerType + " was eaten by " + EntityType.ofClass(entity.getClass())
                            + " from (" + field.getX() + "." + field.getY() + ") "+ "health percentage: "
                            + ((Animal) attacker).getHealthPercentage());
                    break;
                }
            }
        }
    }
}
