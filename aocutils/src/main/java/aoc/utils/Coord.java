package aoc.utils;

public record Coord(int x, int y) {

    public static Coord add(Coord a, Coord b) {
        return new Coord(a.x + b.x, a.y + b.y);
    }

    public Coord moving(Direction dir) {
        return dir.move(this);
    }
}
