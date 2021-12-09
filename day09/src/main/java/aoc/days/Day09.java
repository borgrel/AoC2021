package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Coord;
import aoc.utils.Direction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day09 extends AbstractDay {
    private static final Logger logger = LoggerFactory.getLogger(Day09.class);
    private static final Pattern singleChars = Pattern.compile("");

    private static final int MAX_VALUE = 10;
    private static final List<Direction> adjacent = List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);

    int[][] heightmap;
    Set<Coord> minimums;

    OptionalInt getHeight(Coord coord) {
        if (coord.x() >= 0 && coord.x() < heightmap.length && coord.y() >= 0 && coord.y() < heightmap[coord.x()].length) {
            return OptionalInt.of(heightmap[coord.x()][coord.y()]);
        }
        return OptionalInt.empty();
    }

    boolean isSmaller(Coord current, Direction direction) {
        int currentHeight = getHeight(current).orElseThrow(() -> new IllegalArgumentException("The coordinate '%s' is not valid for array of size (%d, %d)".formatted(current, heightmap.length, heightmap[current.x()].length)));
        int testingHeight = getHeight(current.moving(direction)).orElse(10);
        return currentHeight < testingHeight;
    }

    @Override public void convertInput(@NotNull Stream<String> stream) {
        heightmap = stream.map(singleChars::split)
                .map(array -> Arrays.stream(array)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    @Override //444
    public void part1() {
        minimums = new HashSet<>();

        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[x].length; y++) {
                Coord current = new Coord(x, y);
                if (adjacent.stream().allMatch(dir -> isSmaller(current, dir))) {
                    minimums.add(current);
                }
            }
        }
        logger.debug("Coordinates of minimum locations: {}", minimums);
        result1 = Long.toString(
                minimums.stream()
                        .map(this::getHeight)
                        .mapToInt(OptionalInt::orElseThrow)
                        .map(i -> i + 1)
                        .sum());
    }

    private Set<Coord> basin(Coord minimum) {
        Set<Coord> visited = new HashSet<>();
        Set<Coord> basin = new HashSet<>();

        Deque<Coord> process = new ArrayDeque<>();
        process.addLast(minimum);
        while (!process.isEmpty()) {
            Coord current = process.pollFirst();
            if (visited.contains(current)) continue;

            visited.add(current);
            if (getHeight(current).orElse(9) == 9) continue;
            basin.add(current);

            adjacent.stream().map(dir -> current.moving(dir)).forEach(process::addLast);
        }
        return basin;
    }

    @Override //1168440
    public void part2() {
        result2 = Long.toString(minimums.stream()
                .map(this::basin)
                //.peek(set -> System.out.println(set.size()))
                .map(Set::size)//WTF? u cant map to long because of comparators
                .sorted(Comparator.reverseOrder())
                .mapToLong(i -> i)
                .limit(3)
                .reduce(1, (i, j) -> i * j));
    }
}
