package models.abstracts;

public class Entity {
    private Double weight;
    private Integer maxCountOnField;
    private Integer speed;
    private Double kilogramsOfFoodToFillYouUp;

    public Entity(Double weight, Integer maxCountOnField, Integer speed, Double kilogramsOfFoodToFillYouUp) {
        this.weight = weight;
        this.maxCountOnField = maxCountOnField;
        this.speed = speed;
        this.kilogramsOfFoodToFillYouUp = kilogramsOfFoodToFillYouUp;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getMaxCountOnField() {
        return maxCountOnField;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Double getKilogramsOfFoodToFillYouUp() {
        return kilogramsOfFoodToFillYouUp;
    }
}
