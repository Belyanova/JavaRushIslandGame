package models.services.implementation;

import models.Island;
import models.abstracts.Animal;
import models.enums.DirectionType;
import models.island.Field;
import models.services.MoveService;

public class MoveServiceImplementation implements MoveService {

    private final Island island;

    public MoveServiceImplementation(Island island) {
        this.island = island;
    }

    @Override
    public void move(Animal entity, Field fieldFrom, DirectionType directionType, int speed) {

    }
}
