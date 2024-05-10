import com.fasterxml.jackson.databind.ObjectMapper;
import config.EntityCharacteristicsConfig;
import config.IslandConfig;
import config.PossibilityOfBeingEatenConfig;
import models.plans.Plant;
import models.services.AnimalAction;
import models.services.MultiplyService;
import models.services.implementation.*;
import models.abstracts.Animal;
import models.enums.Action;
import models.enums.EntityType;
import models.services.EatenService;
import models.services.MoveService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static models.services.implementation.islandActionImplementation.*;
import static models.constants.Constants.*;
import static models.services.StatisticsService.printCellStatistics;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        EntityCharacteristicsConfig entityCharacteristicsConfig = new EntityCharacteristicsConfig(
                objectMapper, PATH_TO_ENTITY_CHARACTERISTICS);
        IslandConfig islandConfig = new IslandConfig(PATH_TO_ISLAND_SETTINGS);
        PossibilityOfBeingEatenConfig possibilityOfBeingEatenConfig = new PossibilityOfBeingEatenConfig(
                objectMapper, PATH_TO_POSSIBILITY_OF_BEING_EATEN);

        System.out.println("Default program launch settings:");
        System.out.println("Island width: " + islandConfig.getWidth());
        System.out.println("Island height: " + islandConfig.getHeight());
        System.out.println("Island days: " + islandConfig.getDays());
        System.out.println("Should we leave the default settings? yes/no");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("no")) {
            updateIslandSettings(scanner, islandConfig);
        }

        islandActionImplementation islandActionImplementation = createIsland(islandConfig);
        AnimalAction animalAction = new AnimalActionImplementation();
        MoveService moveService = new MoveServiceImplementation(islandActionImplementation);
        ChooseDirectionServiceImplementation chooseService = new ChooseDirectionServiceImplementation(random);
        EatenService eatenService = new EatenServiceImplementation(islandActionImplementation);
        MultiplyService multiplyService = new MultiplyServiceImplementation(islandActionImplementation);

        fillIslandWithAnimals(entityCharacteristicsConfig, islandActionImplementation, random);
        printCellStatistics(islandActionImplementation, islandConfig);

        for (int i = 1; i < islandConfig.getDays() + 1; i++) {
            System.out.println("Day № " + i);
            System.out.println("________________________________________________");
            List<Runnable> actions = new ArrayList<>();
            executeAnimalsActions(
                    islandActionImplementation, actions, eatenService, random, moveService, chooseService,
                    animalAction, entityCharacteristicsConfig, islandConfig, multiplyService);
            actions.forEach(Runnable::run);
            printCellStatistics(islandActionImplementation, islandConfig);
            refillPlants(islandActionImplementation, entityCharacteristicsConfig);
            islandActionImplementation.removeDeathAnimal();
        }
    }

    private static void updateIslandSettings(Scanner scanner, IslandConfig islandConfig) {
        System.out.println("Enter the width of the island");
        islandConfig.setWidth(scanner.nextInt());
        System.out.println("Enter the height of the island");
        islandConfig.setHeight(scanner.nextInt());
        System.out.println("Enter the days of the island");
        islandConfig.setDays(scanner.nextInt());
    }

    private static void fillIslandWithAnimals(
            EntityCharacteristicsConfig entityCharacteristicsConfig,
            islandActionImplementation islandActionImplementation, Random random) {
        Arrays.stream(EntityType.values()).parallel().forEach(entityType -> {
            int maxCount = getMaxCountOnField(entityCharacteristicsConfig, entityType);
            islandActionImplementation.getIsland().values().parallelStream().forEach(value -> {
                value.addAll(
                        IntStream.range(0, random.nextInt(maxCount))
                                .mapToObj(i -> createEntity(entityCharacteristicsConfig, entityType))
                                .collect(Collectors.toList())
                );
            });
        });
    }

    private static void executeAnimalsActions(
            islandActionImplementation islandActionImplementation, List<Runnable> actions, EatenService eatenService,
            Random random, MoveService moveService, ChooseDirectionServiceImplementation chooseService,
            AnimalAction animalAction, EntityCharacteristicsConfig entityCharacteristicsConfig,
            IslandConfig islandConfig, MultiplyService multiplyService) {
        islandActionImplementation.getIsland().forEach((coordinates, entities) -> {
            List<Animal> animals = entities.stream()
                    .filter(entity -> entity instanceof Animal)
                    .map(entity -> (Animal) entity)
                    .collect(Collectors.toList());

            for (Animal animal : animals) {
                animal.setIsMoved(false);
                Action action = Action.getRandomAction();
                switch (action) {
                    case EAT:
                        actions.add(()-> eatenService.eating(animal, coordinates, islandActionImplementation, random));
                        break;
                    case MOVE:
                        actions.add(() -> moveService.
                                move(animal, coordinates, chooseService.chooseDirection(random), animalAction.
                                        getSpeed(entityCharacteristicsConfig, EntityType.getByEntityClass(
                                                animal.getClass())), islandConfig, islandActionImplementation));
                        break;
                    case MULTIPLY:
                        actions.add(() -> multiplyService.multiply(animal, coordinates,islandActionImplementation));
                        break;
                }
            }
        });
    }

    private static void refillPlants(
            islandActionImplementation islandActionImplementation,
            EntityCharacteristicsConfig entityCharacteristicsConfig) {
        islandActionImplementation.getIsland().forEach((coordinates, entities) -> {
            List<Plant> plants = entities.stream()
                    .filter(entity -> entity instanceof Plant)
                    .map(entity -> (Plant) entity)
                    .collect(Collectors.toList());

            for (Plant plant : plants) {
                islandActionImplementation.refillPlants(plant.getMaxCountOnField(), entityCharacteristicsConfig);
            }
            System.out.println("Plant is refilled on (" + coordinates.getX() + "." + coordinates.getY() + ")");
        });
    }
}
