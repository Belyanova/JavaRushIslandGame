package models.abstracts;

import models.services.AnimalCharacteristics;

public abstract class Animal extends Entity implements AnimalCharacteristics {

    private boolean isMoved;
    private boolean isNewBornAnimal;
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

    public void increaseHealthPercentage(int increaseFor) {
        this.healthPercentage += increaseFor;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public boolean setIsMoved(Boolean moved){
        return isMoved = moved;
    }

    public int getHealthPercentage() {
        return healthPercentage;
    }
}
