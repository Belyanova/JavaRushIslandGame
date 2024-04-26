package models.abstracts;

import models.services.AnimalCharacteristics;

public abstract class Animal extends Entity implements AnimalCharacteristics {

    private boolean isMoved = false;

    public Animal(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    @Override
    public void move() {

    }

    @Override
    public void multiply() {

    }

    @Override
    public void eating() {

    }
}
