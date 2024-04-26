import com.fasterxml.jackson.databind.ObjectMapper;
import config.EntityCharacteristicsConfig;
import config.IslandConfig;
import config.PossibilityOfBeingEatenConfig;
import models.Island;
import models.abstracts.Animal;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;
import models.services.MoveService;
import models.services.implementation.ChooseDirectionServiceImplementation;
import models.services.implementation.MoveServiceImplementation;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static models.constants.Constants.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        EntityCharacteristicsConfig entityCharacteristicsConfig = new EntityCharacteristicsConfig(objectMapper, PATH_TO_ENTITY_CHARACTERISTICS);
        PossibilityOfBeingEatenConfig possibilityOfBeingEatenConfig = new PossibilityOfBeingEatenConfig(objectMapper, PATH_TO_POSSIBILITY_OF_BEING_EATEN);
        IslandConfig islandConfig = new IslandConfig(PATH_TO_ISLAND_SETTINGS);
        //этап изменения дефолтных настроек
        //System.out.println("Будешь что-то менять?");
        //islandConfig.setWidth(10);
        //islandConfig.setHeight(2);

        //создаем остров (100 на 20 клеток)
        Island island = createIsland(islandConfig);
        MoveService moveService = new MoveServiceImplementation(island);

        //параллельными потоками наполняем остров рандомным количеством животных
        Arrays.stream(EntityType.values()).parallel().forEach(entityType -> {
            int maxCount = getMaxCountOnField(entityCharacteristicsConfig, entityType);
            island.getIsland().values().parallelStream().forEach(value -> {
                value.addAll(
                        IntStream.range(0, random.nextInt(maxCount))
                                .mapToObj(i -> createEntity(entityCharacteristicsConfig, entityType))
                                .collect(Collectors.toList())
                );
            });
        });

        //передвижение животных
        ChooseDirectionServiceImplementation chooseDirectionServiceImplementation = new ChooseDirectionServiceImplementation(random);
        int wolfSpeed = getSpeed(entityCharacteristicsConfig, EntityType.WOLF);
        var directonToMove = chooseDirectionServiceImplementation.chooseDirection();

        for (Map.Entry<Field, List<Entity>> fieldListEntry : island.getIsland().entrySet()) {
            Field field = fieldListEntry.getKey();
            List<Animal> entities = fieldListEntry.getValue()
                    .stream()
                    .filter(Animal.class::isInstance)
                    .map(entity -> (Animal) entity )
                    .toList();
            for (Animal entity : entities){
                moveService.move(entity, field, directonToMove, wolfSpeed);
            }
        }

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

    //Определяем максимальное количество животных
    private static Integer getMaxCountOnField(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getMaxCountOnField();
    }

    //Определяем скорость передвижения животных
    private static Integer getSpeed(EntityCharacteristicsConfig entityCharacteristicsConfig, EntityType entityType) {
        return entityCharacteristicsConfig
                .getEntityMapConfig()
                .get(entityType)
                .getSpeed();
    }

    //Создаем поле на острове
    private static Island createIsland(IslandConfig islandConfig) {
        Map<Field, List<Entity>> island = new HashMap<>();
        for (int i = 0; i < islandConfig.getWidth(); i++){
            for (int j = 0; j < islandConfig.getHeight(); j++){
                Field field = new Field(i, j);
                island.put(field, new ArrayList<>());
            }
        }
        return new Island(island);
    }
}