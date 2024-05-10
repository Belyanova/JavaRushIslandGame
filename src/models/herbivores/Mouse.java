package models.herbivores;

import models.abstracts.Entity;

public class Mouse extends Herbivorous {
    public Mouse(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        super(weight, maxCountOnField, speed, kilogramsOfFoodToFillYouUp);
    }

    public Mouse(Entity entity) {
        super(entity.getWeight(), entity.getMaxCountOnField(), entity.getMaxCountOnField(), entity.getKilogramsOfFoodToFillYouUp());
    }
}
