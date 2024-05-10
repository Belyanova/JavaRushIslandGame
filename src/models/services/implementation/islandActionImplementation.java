package models.services.implementation;

import config.EntityCharacteristicsConfig;
import config.IslandConfig;
import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.plans.Grass;
import models.plans.Plant;
import models.services.IslandAction;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class islandActionImplementation implements IslandAction {
    private final Map<Field, List<Entity>> island;

    public Map<Field, List<Entity>> getIsland() {
        return island;
    }

    public islandActionImplementation(Map<Field, List<Entity>> island) {
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
    public void removeAnimalAtCoordinates(Entity entity, Field coordinates) {
        List<Entity> entitiesAtCoordinates = island.get(coordinates);
        if (entitiesAtCoordinates != null) {
            entitiesAtCoordinates.remove(entity);
        } else {
            System.out.println("Failed to delete animal");
        }
    }

    @Override
    public void addedAnimalAtCoordinates(Entity entity, Field coordinates) {
        List<Entity> entitiesAtCoordinates = island.get(coordinates);
        if (entitiesAtCoordinates != null) {
            entitiesAtCoordinates.add(entity);
        } else {
            System.out.println("Failed to add animal");
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
                island.put(value.getKey(), new ArrayList<Entity> (plants));
            }
        }
    }

    public static Entity createEntity(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        try {
            Class<?> entityClass = entityType.getAClass();
            Constructor<?> constructor = entityClass.getConstructor(Entity.class);
            return (Entity) constructor.newInstance(entityCharacteristicsConfig.getEntityMapConfig().get(entityType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unsupported entity type: " + entityType, e);
        }
    }

    public static islandActionImplementation createIsland(IslandConfig islandConfig) {
        Map<Field, List<Entity>> island = new HashMap<>();
        for (int i = 0; i < islandConfig.getWidth(); i++){
            for (int j = 0; j < islandConfig.getHeight(); j++){
                Field field = new Field(i, j);
                island.put(field, new ArrayList<>());
            }
        }
        System.out.println("Создан остров с размерами: " + islandConfig.getHeight() + "  на: " + islandConfig.getWidth());
        return new islandActionImplementation(island);
    }

    public static Integer getMaxCountOnField(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getMaxCountOnField();
    }
}
