package models.abstracts;

import models.services.AnimalCharacteristics;

public abstract class Animal extends Entity implements AnimalCharacteristics {

    private boolean isMoved;
    private int healthPercentage;

    public Animal(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
        this.healthPercentage = 100;
        this.isMoved = false;
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

    public void decreaseHealthPercentage(int decreaseFor) {
        this.healthPercentage -= decreaseFor;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public int getHealthPercentage() {
        return healthPercentage;
    }
}
