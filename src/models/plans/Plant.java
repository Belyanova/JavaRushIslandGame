package models.plans;

import models.abstracts.Entity;

public abstract class Plant extends Entity {
    public Plant(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }
}
