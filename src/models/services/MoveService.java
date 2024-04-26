package models.services;

import models.abstracts.Animal;
import models.enums.DirectionType;
import models.island.Field;

public interface MoveService {
    void move(Animal entity, Field fieldFrom, DirectionType directionType, int speed);
}
