package aoc.days;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Segments {
    private static final char[] standardLayout = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;

    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    private static final int D = 3;
    private static final int E = 4;
    private static final int F = 5;
    private static final int G = 6;


    private static final Map<Integer, Set<Character>> standardNumbers = Map.of(
            ZERO, Set.of('a', 'b', 'c', 'e', 'f', 'g'), //6
            ONE, Set.of('c', 'f'), //2
            TWO, Set.of('a', 'c', 'd', 'e', 'g'), //5
            THREE, Set.of('a', 'c', 'd', 'f', 'g'), //5
            FOUR, Set.of('b', 'c', 'd', 'f'), //4
            FIVE, Set.of('a', 'b', 'd', 'f', 'g'), //5
            SIX, Set.of('a', 'b', 'd', 'e', 'f', 'g'), //6
            SEVEN, Set.of('a', 'c', 'f'), //3
            EIGHT, Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g'), //7
            NINE, Set.of('a', 'b', 'c', 'd', 'f', 'g') //6
    );

    private final char[] scrambledLayout;
    private final Map<Integer, Set<Character>> scrambledNumbers;
    int solvedSegments;

    Segments() {
        scrambledLayout = new char[standardLayout.length];
        scrambledNumbers = new HashMap<>(10);
        solvedSegments = 0;
    }

    @NotNull public static Segments solverOf(List<String> input) {
        Segments solver = new Segments();
        solver.solve(input);
        return solver;
    }

    public static int solverFor(@NotNull Puzzle puzzle) {
        return solverOf(puzzle.patterns()).getOutput(puzzle.outputs());
    }

    public boolean isSolved() {
        return solvedSegments == standardLayout.length;
    }

    private int mapToValue(Set<Character> output) {
        if (!isSolved())
            throw new IllegalStateException("Cannot get unscrambled values before the segments are solved");

        return scrambledNumbers.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(output))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    public int getOutput(@NotNull List<String> outputs) {
        int value = 0;
        for (String output : outputs) {
            value = value * 10 + mapToValue(toSet(output));
        }
        return value;
    }

    private boolean initialise(@NotNull List<String> sorted) {
        if (sorted.get(0).length() == 2) {
            scrambledNumbers.put(ONE, toSet(sorted.get(0)));
        } else return false;
        if (sorted.get(1).length() == 3) {
            scrambledNumbers.put(SEVEN, toSet(sorted.get(1)));
        } else return false;
        if (sorted.get(2).length() == 4) {
            scrambledNumbers.put(FOUR, toSet(sorted.get(2)));
        } else return false;
        if (sorted.get(9).length() == 7) {
            scrambledNumbers.put(EIGHT, toSet(sorted.get(9)));
        } else return false;
        return true;
    }

    private Set<Character> findPattern(@NotNull List<Set<Character>> possibles, Set<Character> remove, int uniqueResult) {
        int numberOfSolutions = 0;
        Set<Character> result = null;
        for (Set<Character> possible : possibles) {
            Set<Character> check = new HashSet<>(possible);
            check.removeAll(remove);
            if (check.size() == uniqueResult) {
                numberOfSolutions++;
                result = possible;
            }
        }
        if (numberOfSolutions != 1)
            throw new IllegalStateException("Removing existing pattern failed to find a unique solution");
        return result;
    }

    public void solve(@NotNull List<String> scrambles) {
        if (scrambles.size() != 10) {
            throw new IllegalArgumentException(
                    "Cannot determine the segments without 10 patterns: %d".formatted(scrambles.size()));
        }
        List<String> sorted = scrambles.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        if (!initialise(sorted)) {
            throw new IllegalStateException(
                    "The patterns did not include single strings with lengths {2,3,4,7} %s".formatted(sorted));
        }

        List<Set<Character>> lengthsFive = sorted.subList(3, 6).stream().map(this::toSet).toList();
        List<Set<Character>> lengthsSix = sorted.subList(6, 9).stream().map(this::toSet).toList();

        // 1, 4, 7, 8 // 6, 9, 0 // 3 -> 2,5
        scrambledNumbers.put(SIX, findPattern(lengthsSix, scrambledNumbers.get(ONE), 5));
        scrambledNumbers.put(NINE, findPattern(lengthsSix, scrambledNumbers.get(FOUR), 2));

        scrambledNumbers.put(ZERO, lengthsSix.stream()
                .filter(set -> !set.equals(scrambledNumbers.get(SIX)))
                .filter(set -> !set.equals(scrambledNumbers.get(NINE)))
                .findFirst().orElseThrow());

        scrambledNumbers.put(THREE, findPattern(lengthsFive, scrambledNumbers.get(ONE), 3));
        scrambledNumbers.put(TWO, findPattern(lengthsFive, scrambledNumbers.get(FOUR), 3));

        scrambledNumbers.put(FIVE, lengthsFive.stream()
                .filter(set -> !set.equals(scrambledNumbers.get(TWO)))
                .filter(set -> !set.equals(scrambledNumbers.get(THREE)))
                .findFirst().orElseThrow());

        findSegments();
    }

    private void findLetter(int letter, Set<Character> initial, Set<Character> remove) {
        Set<Character> findLetter = new HashSet<>(initial);
        findLetter.removeAll(remove);
        if (findLetter.size() != 1) {
            throw new IllegalStateException("Oops, made a booboo searching for '" + (char) ('a' + letter) + "'");
        }
        scrambledLayout[letter] = findLetter.stream().findFirst().orElseThrow();
        solvedSegments++;
    }

    private void findSegments() {
        Set<Character> temp = new HashSet<>(scrambledNumbers.get(FOUR));
        temp.addAll(scrambledNumbers.get(SEVEN));

        findLetter(A, scrambledNumbers.get(SEVEN), scrambledNumbers.get(ONE));
        findLetter(B, scrambledNumbers.get(FOUR), scrambledNumbers.get(THREE));
        findLetter(C, scrambledNumbers.get(ONE), scrambledNumbers.get(FIVE));
        findLetter(D, scrambledNumbers.get(EIGHT), scrambledNumbers.get(ZERO));
        findLetter(E, scrambledNumbers.get(EIGHT), scrambledNumbers.get(NINE));
        findLetter(F, scrambledNumbers.get(ONE), scrambledNumbers.get(TWO));

        findLetter(G, scrambledNumbers.get(FIVE), temp);
    }

    private Set<Character> toSet(@NotNull String input) {
        char[] chars = input.toCharArray();
        return IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .collect(Collectors.toSet());


    }
}
