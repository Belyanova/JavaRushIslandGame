package models.plans;

import models.abstracts.Entity;

public class Grass extends Plant {
    public Grass(Double weight,
                 Integer maxCountOnField,
                 Integer speed,
                 Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    public Grass(Entity entity) {
        super(entity.getWeight(), entity.getMaxCountOnField(), entity.getMaxCountOnField(), entity.getKilogramsOfFoodToFillYouUp());
    }
}
