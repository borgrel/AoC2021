package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Regex;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class Day10 extends AbstractDay {
    private static final Logger logger = LoggerFactory.getLogger(Day10.class);
    private static final Map<String, Chunk> mapping = Chunk.mapping();

    private List<String> input;
    private List<ArrayDeque<Chunk>> filteredInput;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream.toList();
    }

    public Optional<Chunk> findError(String input) {
        ArrayDeque<Chunk> syntax = new ArrayDeque<>();

        for (String value : Regex.splitIntoSingleChars(input)) {
            Chunk current = mapping.get(value);
            if (current.isOpening()) {
                syntax.addFirst(current);
                logger.trace("Added opening bracket: {}, Stack is: {}", current, syntax);
            } else {
                Chunk compare = syntax.pollFirst();
                logger.trace("Current Closing: {}, compared opening; {} ... result = {}", current, compare, current.isMatchingClosing(compare));
                if (!current.isMatchingClosing(compare)) {
                    logger.debug("Match failed! Current is: {} Compared to {} Stack is {}", current, compare, syntax);

                    return Optional.of(current);
                }
            }
        }
        filteredInput.add(syntax);
        return Optional.empty();
    }

    @Override //216297
    public void part1() {
        List<Chunk> errors = new ArrayList<>();
        filteredInput = new ArrayList<>();

        for (String line : input) {
            findError(line).ifPresent(errors::add);
        }
        logger.debug("Corrupted Data: {}", errors);
        result1 = Long.toString(
                errors.stream().mapToInt(Chunk::getErrorValue).sum());
    }

    private long calculateScore(ArrayDeque<Chunk> openChunks) {
        long score = 0;
        while (!openChunks.isEmpty()) {
            score = score * 5 + openChunks.pollFirst().getErrorValue();
        }
        return score;
    }

    @Override //2165057169
    public void part2() {
        List<Long> sortedScores = filteredInput.stream()
                .map(this::calculateScore)
                .sorted()
                .toList();
        logger.debug("Sorted Scores: {}", sortedScores);
        result2 = Long.toString(
                sortedScores.get(sortedScores.size() / 2)
        );
    }
}
