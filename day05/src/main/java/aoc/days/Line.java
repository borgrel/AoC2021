package aoc.days;

import aoc.utils.Coord;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record Line(Coord c1, Coord c2) {
    public static final Pattern arrow = Pattern.compile("\\s*->\\s*");

    public static Line of(String notation) {
        String[] values = arrow.split(notation);
        if (values.length != 2)
            throw new IllegalArgumentException("The string '%s' does not match the format 'a,b -> c,d'");

        return new Line(coordFromString(values[0]), coordFromString(values[1]));
    }

    private static aoc.utils.Coord coordFromString(String input) {
        return aoc.utils.Coord.of(Arrays.stream(Regex.splitAtCommas(input))
                .mapToInt(Integer::parseInt)
                .toArray());
    }

    public Stream<Coord> points() {
        int xDiff = increment(c2.x(), c1.x());
        int yDiff = increment(c2.y(), c1.y());

        return Stream.concat(Stream.iterate(c1,
                        coord -> coord.x() != c2.x() || coord.y() != c2.y(),
                        coord -> new Coord(coord.x() + xDiff, coord.y() + yDiff)),
                Stream.of(c2));
    }

    public Stream<Coord> pointsWithoutDiagonals() {
        if (c1.x() != c2().x() && c1.y() != c2().y()) return Stream.empty();
        return points();
    }

    private int increment(int a, int b) {
        int diff = a - b;
        if (diff == 0) return diff;
        if (diff > 0) return 1;
        return -1; //diff < 0
    }
}
