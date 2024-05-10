package models.predators;

import models.abstracts.Entity;
public class Eagle extends Predator {
    public Eagle(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    public Eagle(Entity entity) {
        super(entity.getWeight(), entity.getMaxCountOnField(), entity.getMaxCountOnField(), entity.getKilogramsOfFoodToFillYouUp());
    }
}
