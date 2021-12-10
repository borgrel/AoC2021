package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Regex;
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

    int resumeFrom;

    //suggestion from Roukanken#8774 map bingo numbers to their order, substitute boards numbers for their order value
    // find max for each row and column, min of those is the time when that board would have won, sort by those values

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        List<List<String>> input = stream.collect(Utils.toTextBlock()).toList();

        if (input.get(0).size() != 1)
            throw new IllegalStateException("The bingo numbers are not present at the start of the file");

        bingoNumbers = new IntArrayList(
                input.get(0).stream()
                        .map(Regex::splitAtCommas)
                        .flatMap(Arrays::stream)
                        .mapToInt(Integer::parseInt)
                        .toArray());
        bingoBoards = input.stream().skip(1).map(BingoBoard::new).toList();


        bingoMap = new BingoMap();
        bingoBoards.stream().forEach(board -> board.mapPositions(bingoMap));
    }

    @Override //10680
    public void part1() {
        for (int i = 0; i < bingoNumbers.size(); i++) {
            int number = bingoNumbers.getInt(i);
            bingoMap.markPositions(number);
            Optional<BingoBoard> winner = bingoMap.boardsWithNumber(number).filter(BingoBoard::hasWon).findAny();
            if (winner.isPresent()) {
                result1 = Long.toString(winner.orElseThrow().sumUnmarked() * number);
                alreadyWon.add(winner.orElseThrow());
                resumeFrom = i + 1;
                return;
            }
        }
    }

    @Override //31892
    public void part2() {
        AtomicInteger wonBoards = new AtomicInteger(1);

        for (int i = resumeFrom; i < bingoNumbers.size(); i++) {
            int number = bingoNumbers.getInt(i);
            bingoMap.markPositions(number);

            bingoMap.boardsWithNumber(number)
                    .filter(Predicate.not(alreadyWon::contains))
                    .filter(BingoBoard::hasWon)
                    .forEach(winner -> {
                        wonBoards.incrementAndGet();
                        alreadyWon.add(winner);
                        if (wonBoards.get() == bingoBoards.size()) {
                            result2 = Long.toString(winner.sumUnmarked() * number);
                        }
                    });
        }
    }
}
