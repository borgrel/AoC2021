package aoc.days;

import aoc.utils.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 extends AbstractDay {
    private static final Logger logger = LoggerFactory.getLogger(Day13.class);
    private static final int WIDTH = 5 * 8; //6 chars per letter x 8 letters
    private static final int HEIGHT = 6;

    private static final char EMPTY = ' ';
    private static final char FILLED = '\u2588';

    Set<Coord> dots;
    List<FoldInstruction> instructions;

    Set<Coord> afterFold1;
    Set<Coord> afterAllFolds;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        List<List<String>> temp = stream.collect(Utils.toTextBlock())
                .toList();

        if (temp.size() != 2)
            throw new IllegalStateException("Input did not contain coordinates and instructions separated by an empty line");

        dots = temp.get(0).stream().map(Regex::splitAtCommas)
                .map(array -> Arrays.stream(array)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .map(Coord::of)
                .collect(Collectors.toSet());

        instructions = temp.get(1).stream()
                .map(FoldInstruction::fromString)
                .toList();
    }

    private Coord foldDot(Coord coord, FoldInstruction instruction) {
        int changing;
        if (instruction.direction() == Direction.UP) {
            changing = coord.y();
        } else {
            changing = coord.x();
        }
        if (changing < instruction.location()) return coord;

        changing = 2 * instruction.location() - changing;

        if (instruction.direction() == Direction.UP) {
            return new Coord(coord.x(), changing);
        } else {
            return new Coord(changing, coord.y());
        }
    }

    @Override //687
    public void part1() {
        FoldInstruction firstFold = instructions.get(0);

        afterFold1 = dots.stream().map(coord -> foldDot(coord, firstFold)).collect(Collectors.toSet());

        result1 = Integer.toString(afterFold1.size());
    }

    @Override //FGKCKBZG
    public void part2() {
        afterAllFolds = dots;
        for (FoldInstruction instruction : instructions) {
            afterAllFolds = afterAllFolds.stream().map(coord -> foldDot(coord, instruction)).collect(Collectors.toSet());
        }
        logger.debug("Final Points: {}", afterAllFolds);

        char[][] code = new char[HEIGHT][WIDTH];
        for (char[] row : code) {
            Arrays.fill(row, EMPTY);
        }
        afterAllFolds.stream()
                .forEach(coord -> code[coord.y()][coord.x()] = FILLED);

        StringBuilder sb = new StringBuilder();
        for (char[] row : code) {
            sb.append(row).append("\n");
        }
        logger.info("Answer:\n{}", sb);
        result2 = "";
    }
}
