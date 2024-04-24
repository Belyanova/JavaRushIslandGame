package models.services;

import java.util.Map;

public class EntityJsonData {
    private Map<String, EntityData> entities;

    public Map<String, EntityData> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, EntityData> entities) {
        this.entities = entities;
    }

    public static class EntityData {
        private double weight;
        private int maxCountOnField;
        private Integer speed;
        private Double kilogramsOfFoodToFillYouUp;

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
        public int getMaxCountOnField() { return maxCountOnField; }
        public void setMaxCountOnField(int maxCountOnField) { this.maxCountOnField = maxCountOnField; }
        public Integer getSpeed() { return speed; }
        public void setSpeed(Integer speed) { this.speed = speed; }
        public Double getKilogramsOfFoodToFillYouUp() { return kilogramsOfFoodToFillYouUp; }
        public void setKilogramsOfFoodToFillYouUp(Double kilogramsOfFoodToFillYouUp) {
            this.kilogramsOfFoodToFillYouUp = kilogramsOfFoodToFillYouUp;
        }
    }
}
