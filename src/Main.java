import com.fasterxml.jackson.databind.ObjectMapper;
import config.EntityCharacteristicsConfig;
import config.FieldSizeConfig;
import config.PossibilityOfBeingEatenConfig;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.services.ChooseDirectionService;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Map<Field, List<Entity>> island = new HashMap<>();
        Random random = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        EntityCharacteristicsConfig entityCharacteristicsConfig = new EntityCharacteristicsConfig(objectMapper, "resources/entity_characteristics.json");
        PossibilityOfBeingEatenConfig possibilityOfBeingEatenConfig = new PossibilityOfBeingEatenConfig(objectMapper, "resources/possibility_of_being_eaten.json");

        FieldSizeConfig fieldSizeConfig = new FieldSizeConfig(100, 20);

        ChooseDirectionService chooseDirectionService = new ChooseDirectionService(random);

        createField(fieldSizeConfig, island);

        /*писали на курсе
        island.values().forEach(value -> IntStream.range(0, random.nextInt(maxPlantsOnFields))
                .mapToObj(i -> createGrass(entityCharacteristicsConfig))
                .forEach(value::add));

        island.values().forEach(value -> IntStream.range(0, random.nextInt(maxWolfOnField))
                .mapToObj(i -> createWolf(entityCharacteristicsConfig))
                .forEach(value::add));

        island.values().forEach(value -> IntStream.range(0, random.nextInt(getMaxCountOnField(entityCharacteristicsConfig,EntityType.GRASS)))
                .mapToObj(i -> createEntity(entityCharacteristicsConfig,EntityType.GRASS))
                .forEach(value::add));

        island.values().forEach(value -> IntStream.range(0, random.nextInt(getMaxCountOnField(entityCharacteristicsConfig,EntityType.WOLF)))
                .mapToObj(i -> createEntity(entityCharacteristicsConfig,EntityType.WOLF))
                .forEach(value::add));*/

        //параллельными потоками наполняем остров
        Arrays.stream(EntityType.values()).parallel().forEach(entityType -> {
            int maxCount = getMaxCountOnField(entityCharacteristicsConfig, entityType);
            island.values().parallelStream().forEach(value -> {
                value.addAll(
                        IntStream.range(0, random.nextInt(maxCount))
                                .mapToObj(i -> createEntity(entityCharacteristicsConfig, entityType))
                                .collect(Collectors.toList())
                );
            });
        });

        int wolfSpeed = getSpeed(entityCharacteristicsConfig, EntityType.WOLF);
        var directonToMove = chooseDirectionService.chooseDirection();

        System.out.println(island);
    }

    //Создаем животных, передавая соответствующие данные из конфигов
    private static Entity createEntity(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        try {
            Class<?> entityClass = entityType.getAClass();
            Constructor<?> constructor = entityClass.getConstructor(Entity.class);
            return (Entity) constructor.newInstance(entityCharacteristicsConfig.getEntityMapConfig().get(entityType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unsupported entity type: " + entityType, e);
        }
    }

    private static Integer getMaxCountOnField(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getMaxCountOnField();
    }

    private static Integer getSpeed(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getSpeed();
    }

    private static void createField(FieldSizeConfig fieldSizeConfig, Map<Field, List<Entity>> island) {
        for (int i = 0; i < fieldSizeConfig.getWidth(); i++){
            for (int j = 0; j < fieldSizeConfig.getHeight(); j++){
                Field field = new Field(i, j);
                island.put(field, new ArrayList<>());
            }
        }
    }
}