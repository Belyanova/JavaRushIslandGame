package models;

import config.EntityCharacteristicsConfig;
import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.plans.Grass;
import models.plans.Plant;
import models.services.IslandAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Island implements IslandAction {
    private final Map<Field, List<Entity>> island;

    public Map<Field, List<Entity>> getIsland() {
        return island;
    }

    public Island(Map<Field, List<Entity>> island) {
        this.island = island;
    }

    @Override
    public void removeDeathAnimal(){
        for (Entry<Field, List<Entity>> fieldListEntry : island.entrySet()) {
            var allAnimal = fieldListEntry.getValue();
            for(Entity entity : allAnimal){
                if (entity instanceof Animal){
                    Animal animal = (Animal) entity;
                    var healthPercentage = animal.getHealthPercentage();
                    if (healthPercentage <= 0){
                        allAnimal.remove(entity);
                    }
                }
            }
        }
    }

    @Override
    public void refillPlants(int maxCountOfPlantInOneField, EntityCharacteristicsConfig entityCharacteristicsConfig){
        for(var value : island.entrySet()){
            var totalCountOfPlants = island
                    .values()
                    .stream()
                    .filter(entity -> entity instanceof Plant).count();
            if(totalCountOfPlants < maxCountOfPlantInOneField){
                List<Plant> plants = new ArrayList<>();
                for (int i = 0; i < maxCountOfPlantInOneField - totalCountOfPlants; i++) {
                    Plant plant = new Grass(entityCharacteristicsConfig.getEntityMapConfig().get(EntityType.GRASS));
                    plants.add(plant);
                }
                System.out.println(plants);
                island.put(value.getKey(), new ArrayList<Entity> (plants));
            }
        }
    }

}
