package models.services;

import models.abstracts.Entity;
import models.enums.EntityType;

import java.util.Map;

import static config.PossibilityOfBeingEatenConfig.possibilityOfBeingEaten;
import static config.PossibilityOfBeingEatenConfig.probabilityThreshold;

public interface EatenService {
    static boolean isEaten(Entity attacker, Entity prey) {
        EntityType attackerType = EntityType.ofClass(attacker.getClass());
        EntityType preyType = EntityType.ofClass(prey.getClass());

        // Получаем вероятность атаки атакующего на жертву из конфигурации
        Map<EntityType, Integer> attackerProbabilities = possibilityOfBeingEaten.get(attackerType);
        if (attackerProbabilities != null) {
            Integer probability = attackerProbabilities.get(preyType);
            if (probability != null) {
                // Проверяем, превышает ли вероятность атаки заданный порог
                return probability >= probabilityThreshold;
            }
        }

        // Если в конфигурации нет информации о вероятности атаки атакующего на жертву,
        // предполагаем, что атакующее животное не может съесть жертву
        return false;
    }
}
