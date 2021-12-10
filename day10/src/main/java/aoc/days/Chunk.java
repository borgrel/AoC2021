package aoc.days;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Chunk {
    ROUND_OPEN("(", 1, true),
    SQUARE_OPEN("[", 2, true),
    CURLY_OPEN("{", 3, true),
    POINTY_OPEN("<", 4, true),
    ROUND_CLOSE(")", 3, false),
    SQUARE_CLOSE("]", 57, false),
    CURLY_CLOSE("}", 1197, false),
    POINTY_CLOSE(">", 25137, false);

    private final String ch;
    private final int score;
    private final boolean isOpening;

    Chunk(String ch, int value, boolean isOpening) {
        this.ch = ch;
        this.score = value;
        this.isOpening = isOpening;
    }

    public static Map<String, Chunk> mapping() {
        return Arrays.stream(Chunk.values())
                .collect(Collectors.toMap(Chunk::getBracket, Function.identity()));
    }

    public String getBracket() {
        return ch;
    }

    public int getErrorValue() {
        return score;
    }

    public boolean isOpening() {
        return isOpening;
    }

    public boolean isClosing() {
        return !isOpening;
    }

    public boolean isMatchingClosing(Chunk opening) {
        if (!opening.isOpening())
            throw new IllegalArgumentException("The parameter '%s' is not an opening value and cannot be compared to closing value".formatted(opening.ch));
        if (!isClosing())
            throw new IllegalArgumentException("This value '%s' is not an closing value and cannot be compared to an opening parameter".formatted(opening.ch));
        return ordinal() - 4 == opening.ordinal();
    }
}
