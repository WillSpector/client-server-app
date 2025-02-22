package models;

/**
 * Представляет собой координаты с двумя значениями: X и Y.
 * Используется для... так и не понял зачем координаты группе:(.
 */

public class Coordinates {
    private final Long x;
    private final double y;

    public Coordinates(Long x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates: X = " + x + " Y = " + y + ";";
    }
}
