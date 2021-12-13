package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 extends AbstractDay {
    private static final Logger logger = LoggerFactory.getLogger(Day12.class);

    private static final Pattern dash = Pattern.compile("-");
    private static final Predicate<String> allLowercase = Pattern.compile("[a-z]+").asMatchPredicate();
    private static final String BEGIN = "start";
    private static final String END = "end";

    Map<String, List<String>> graph;
    Set<String> smallcaves;

    private Stream<String[]> bothDirections(String[] oneDirection) {
        if (oneDirection.length != 2)
            throw new IllegalArgumentException("The array does not represent an edge, its length is not 2: %s".formatted(Arrays.toString(oneDirection)));
        String[] otherDirection = {oneDirection[1], oneDirection[0]};
        return Stream.of(oneDirection, otherDirection);
    }

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        graph = stream.map(dash::split)
                .flatMap(this::bothDirections)
                .collect(Collectors.groupingBy(array -> array[0],
                        Collectors.mapping(array -> array[1], Collectors.toList())));

        smallcaves = graph.keySet().stream()
                .filter(allLowercase)
                .collect(Collectors.toSet());

        //logger.debug("Edges: {}", graph);
        //logger.debug("Smallcaves: {}", smallcaves);
    }

    @Override
    public void part1() {
        Deque<String> toVisit = new ArrayDeque<>();
        Deque<String> currentPath = new ArrayDeque<>();

        toVisit.addLast(BEGIN);
        while (!toVisit.isEmpty()) {
            String current = toVisit.pollLast();
            currentPath.add(current);

        }
        
    }

    @Override
    public void part2() {

    }
}
