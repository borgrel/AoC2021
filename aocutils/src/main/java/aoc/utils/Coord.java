package aoc.utils;

import java.util.Arrays;

public record Coord(int x, int y) {

    public static Coord add(Coord a, Coord b) {
        return new Coord(a.x + b.x, a.y + b.y);
    }

    public static Coord of(int x, int y) {
        return new Coord(x, y);
    }

    public static Coord of(int[] coords) {
        if (coords.length != 2) {
            throw new IllegalArgumentException("Unable to make an (x,y) coordinate without exactly 2 values: '%s'"
                    .formatted(Arrays.toString(coords)));
        }
        return of(coords[0], coords[1]);
    }

    public Coord moving(Direction dir) {
        return dir.move(this);
    }
}
