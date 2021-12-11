package aoc.days;

import aoc.utils.Coord;
import aoc.utils.Direction;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DumboArray {
    private static final List<Direction> neighbours =
            List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
                    Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);

    final DumboCoord[][] dumbos;

    DumboArray(Stream<String> stream) {
        final Dumbo[][] dumbosInput = stream.map(Regex::splitIntoSingleChars)
                .map(array -> Arrays.stream(array)
                        .mapToInt(Integer::parseInt)
                        .mapToObj(Dumbo::new)
                        .toArray(Dumbo[]::new))
                .toArray(Dumbo[][]::new);

        dumbos = new DumboCoord[dumbosInput.length][dumbosInput[0].length];
        for (int x = 0; x < dumbos.length; x++) {
            for (int y = 0; y < dumbos[x].length; y++) {
                dumbos[x][y] = new DumboCoord(dumbosInput[x][y], Coord.of(x, y));
            }
        }
    }

    DumboCoord get(int x, int y) {
        return dumbos[x][y];
    }

    DumboCoord get(Coord coord) {
        return get(coord.x(), coord.y());
    }

    Stream<DumboCoord> getNeighbours(int x, int y) {
        return getNeighbours(new Coord(x, y));
    }

    Stream<DumboCoord> stream() {
        return Arrays.stream(dumbos)
                .flatMap(Arrays::stream);
    }

    Stream<DumboCoord> getNeighbours(Coord coord) {
        return neighbours.stream()
                .map(coord::moving)
                .filter(neighbour -> neighbour.x() >= 0 && neighbour.x() < dumbos.length)
                .filter(neighbour -> neighbour.y() >= 0 && neighbour.y() < dumbos[neighbour.x()].length)
                .map(this::get);
    }

    Stream<DumboCoord> getNeighbours(DumboCoord dumbo) {
        return getNeighbours(dumbo.location());
    }

    @Override
    public String toString() {
        return Arrays.stream(dumbos)
                .map(array -> Arrays.stream(array)
                        .map(DumboCoord::dumbo)
                        .mapToInt(Dumbo::energy)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
