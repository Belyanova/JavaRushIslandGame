package models.herbivores;

import models.abstracts.Entity;

public class Deer extends Herbivorous {
    public Deer(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    public Deer(Entity entity) {
        super(entity.getWeight(), entity.getMaxCountOnField(), entity.getMaxCountOnField(), entity.getKilogramsOfFoodToFillYouUp());
    }
}
