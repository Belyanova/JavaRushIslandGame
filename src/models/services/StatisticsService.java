package models.services;

import config.IslandConfig;
import models.Island;
import models.abstracts.Entity;
import models.enums.EntityType;
import models.island.Field;

import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    public static void printCellStatistics(Island island, IslandConfig islandConfig) {
        System.out.println("Создан остров с размерами: " + islandConfig.getHeight() + "  на: " + islandConfig.getWidth());

        Map<Field, Map<EntityType, Integer>> cellStatistics = new HashMap<>();
        island.getIsland().forEach((coordinates, entities) -> {
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
    }
}
