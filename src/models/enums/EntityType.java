package models.enums;

import models.herbivores.*;
import models.plans.Grass;
import models.predators.*;

public enum EntityType {
    WOLF("wolf", Wolf.class),
    PYTHON("python", Python.class),
    FOX("fox", Fox.class),
    BEAR("bear", Bear.class),
    EAGLE("eagle", Eagle.class),
    HORSE("horse", Horse.class),
    DEER("deer", Deer.class),
    RABBIT("rabbit", Rabbit.class),
    MOUSE("mouse", Mouse.class),
    GOAT("goat", Goat.class),
    SHEEP("sheep", Sheep.class),
    BOAR("boar", Boar.class),
    BUFFALO("buffalo", Buffalo.class),
    DUCK("duck", Duck.class),
    CATERPILLAR("caterpillar", Caterpillar.class),
    GRASS("grass", Grass.class);

    private String type;
    private Class aclass;

    EntityType(String type, Class aclass) {
        this.type = type;
        this.aclass = aclass;
    }

    public Class<?> getAClass() {
        return aclass;
    }
}
