package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Utils;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Day04 extends AbstractDay {
    IntList bingoNumbers;
    List<BingoBoard> bingoBoards;
    Set<BingoBoard> alreadyWon = new HashSet<>();
    BingoMap bingoMap;

    int tempPause;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        List<List<String>> input = stream.collect(Utils.toTextBlock()).toList();

        if (input.get(0).size() != 1)
            throw new IllegalStateException("The bingo numbers are not present at the start of the file");

        bingoNumbers = new IntArrayList(
                input.get(0).stream()
                        .map(str -> str.split(","))
                        .flatMap(Arrays::stream)
                        .mapToInt(Integer::parseInt)
                        .toArray());
        bingoBoards = input.stream().skip(1).map(BingoBoard::new).toList();


        bingoMap = new BingoMap();
        bingoBoards.stream().forEach(board -> board.mapPositions(bingoMap));
    }

    @Override //10680
    public void part1() {
        for (int number : bingoNumbers) {
            bingoMap.markPositions(number);
            Optional<BingoBoard> winner = bingoMap.boardsWithNumber(number).filter(BingoBoard::hasWon).findAny();
            if (winner.isPresent()) {
                result1 = Long.toString(winner.orElseThrow().sumUnmarked() * number);
                alreadyWon.add(winner.orElseThrow());
                tempPause = number;
                return;
            }
        }
    }

    @Override //31892
    public void part2() {
        AtomicInteger wonBoards = new AtomicInteger(1);
        boolean skipping = true;

        for (int number : bingoNumbers) {
            if (skipping) {
                if (number == tempPause)
                    skipping = false;
                continue;
            }
            bingoMap.markPositions(number);

            bingoMap.boardsWithNumber(number)
                    .filter(Predicate.not(alreadyWon::contains))
                    .filter(BingoBoard::hasWon)
                    .forEach(winner -> {
                        wonBoards.incrementAndGet();
                        alreadyWon.add(winner);
                        if (wonBoards.get() == bingoBoards.size()) {
                            result2 = Long.toString(winner.sumUnmarked() * number);
                            return;
                        }
                    });
        }
    }
}
