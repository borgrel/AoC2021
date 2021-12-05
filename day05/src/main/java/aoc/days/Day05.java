package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day05 extends AbstractDay {
    List<Line> input;

    @Override //5145
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream.map(Line::of).toList();
    }

    private int countIntersections(boolean withDiags) {
        Set<Coord> dangerCoords = new HashSet<>();
        Set<Coord> duplicateValues = new HashSet<>();

        input.stream().flatMap(withDiags ? Line::points : Line::pointsWithoutDiag)
                .forEach(coord -> {
                    if (dangerCoords.contains(coord)) {
                        duplicateValues.add(coord);
                    } else
                        dangerCoords.add(coord);
                });
        return duplicateValues.size();
    }

    @Override //16518
    public void part1() {
        result1 = Integer.toString(countIntersections(false));
    }

    @Override
    public void part2() {
        result2 = Integer.toString(countIntersections(true));
    }
}
