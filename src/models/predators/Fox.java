package models.predators;

import models.abstracts.Entity;

public class Fox extends Predator {
    public Fox(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    public Fox(Entity entity) {
        super(entity.getWeight(), entity.getMaxCountOnField(), entity.getMaxCountOnField(), entity.getKilogramsOfFoodToFillYouUp());
    }
}
