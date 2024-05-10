package models.services;

import config.IslandConfig;
import models.services.implementation.islandActionImplementation;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;

import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    public static void printCellStatistics(islandActionImplementation islandActionImplementation, IslandConfig islandConfig) {
        System.out.println("________________________________________________");
        Map<Field, Map<EntityType, Integer>> cellStatistics = new HashMap<>();
        islandActionImplementation.getIsland().forEach((coordinates, entities) -> {
            Map<EntityType, Integer> entityCounts = new HashMap<>();
            for (Entity entity : entities) {
                EntityType entityType = EntityType.getByEntityClass(entity.getClass());
                int count = entityCounts.getOrDefault(entityType, 0);
                entityCounts.put(entityType, count + 1);
            }
            if (!entityCounts.isEmpty()) {
                cellStatistics.put(coordinates, entityCounts);
            }
        });

        cellStatistics.forEach((cell, entityCounts) -> {
            System.out.println("Coordinates: (" + cell.getX() + ", " + cell.getY() + ")");
            entityCounts.forEach((entityType, count) -> {
                System.out.println(" - " + entityType + ": " + count);
            });
        });
        System.out.println("________________________________________________");
    }
}
