package aoc.days;

import java.util.regex.Pattern;

public record Coord(int x, int y) {
    private static final Pattern comma = Pattern.compile(",");

    public static Coord of(String commaSeparated) {
        String[] values = comma.split(commaSeparated);
        if (values.length != 2)
            throw new IllegalArgumentException("The string '%s' does not match the format 'a,b'".formatted(commaSeparated));

        return new Coord(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }
}
