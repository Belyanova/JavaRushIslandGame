package models.herbivores;

import models.abstracts.Animal;

public abstract class Herbivorous extends Animal {
    public Herbivorous(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }
}
