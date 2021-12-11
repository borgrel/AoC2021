package aoc.utils;

public enum Direction {
    UP(new Coord(0, 1)),
    DOWN(new Coord(0, -1)),
    LEFT(new Coord(-1, 0)),
    RIGHT(new Coord(1, 0)),
    UP_LEFT(new Coord(-1, 1)),
    UP_RIGHT(new Coord(1, 1)),
    DOWN_LEFT(new Coord(-1, -1)),
    DOWN_RIGHT(new Coord(1, -1)),
    FORWARD(null),
    BACKWARD(null),
    NORTH(new Coord(0, 1)),
    EAST(new Coord(1, 0)),
    SOUTH(new Coord(0, -1)),
    WEST(new Coord(-1, 0));

    Coord directionVector;

    Direction(Coord directionVector) {
        this.directionVector = directionVector;
    }

    Coord move(Coord from) {
        return Coord.add(from, directionVector);
    }
}
