package models.services.implementation;

import models.enums.DirectionType;

import java.util.Random;

public class ChooseDirectionServiceImplementation {

    private final Random random;

    public ChooseDirectionServiceImplementation(Random random) {
        this.random = random;
    }

    public DirectionType chooseDirection(){
        random.nextInt(4);

        return DirectionType.values()[random.nextInt(DirectionType.values().length)];
    }
}
