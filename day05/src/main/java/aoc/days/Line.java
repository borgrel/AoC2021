package aoc.days;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record Line(Coord c1, Coord c2) {
    public static final Pattern arrow = Pattern.compile("\\s*->\\s*");

    public static Line of(String notation) {
        String[] values = arrow.split(notation);
        if (values.length != 2)
            throw new IllegalArgumentException("The string '%s' does not match the format 'a,b -> c,d'");

        return new Line(Coord.of(values[0]), Coord.of(values[1]));
    }

    public Stream<Coord> points() {
        int xDiff = increment(c2.x(), c1.x());
        int yDiff = increment(c2.y(), c1.y());

        List<Coord> results = new ArrayList<>();
        Coord current = c1;
        while (current.x() != c2.x() || current.y() != c2.y()) {
            results.add(current);
            current = new Coord(current.x() + xDiff, current.y() + yDiff);
        }
        results.add(current);
        return results.stream();
        /* not working?
            return Stream.iterate(c1, coord -> coord.x() <= c2.x() && coord.y() <= c2.y(),
                coord -> new Coord(coord.x() + xDiff, coord.y() + yDiff));
         */
    }

    public Stream<Coord> pointsWithoutDiag() {
        int xDiff = increment(c2.x(), c1.x());
        int yDiff = increment(c2.y(), c1.y());
        if (xDiff != 0 && yDiff != 0) return Stream.empty();
        return points();
    }

    private int increment(int a, int b) {
        int diff = a - b;
        if (diff == 0) return diff;
        if (diff > 0) return 1;
        return -1; //diff < 0
    }
}
