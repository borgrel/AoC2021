package aoc.days;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class BingoMap {
    private static final int HIGHEST_NUMBER = 99;

    Object[] numberToPosition;

    public BingoMap() {
        numberToPosition = new Object[HIGHEST_NUMBER + 1];
    }

    @SuppressWarnings("unchecked")
    public void addPosition(final int number, @NotNull final BoardPosition position) {
        if (numberToPosition[number] == null)
            numberToPosition[number] = new HashSet<BoardPosition>();

        ((Set<BoardPosition>) numberToPosition[number]).add(position);
    }

    @SuppressWarnings("unchecked")
    public void markPositions(int number) {
        ((Set<BoardPosition>) numberToPosition[number])
                .forEach(position -> position.board().markPosition(position));
    }

    @SuppressWarnings("unchecked")
    public Stream<BingoBoard> boardsWithNumber(int number) {
        return ((Set<BoardPosition>) numberToPosition[number]).stream().map(BoardPosition::board);
    }


}
