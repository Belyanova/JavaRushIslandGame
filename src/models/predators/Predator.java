package models.predators;

import models.abstracts.Animal;

public abstract class Predator extends Animal {
    public Predator(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }
}
