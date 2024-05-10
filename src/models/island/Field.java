package models.island;

import java.util.Objects;

public class Field {
    private int x;
    private int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return x == field.x && y == field.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
