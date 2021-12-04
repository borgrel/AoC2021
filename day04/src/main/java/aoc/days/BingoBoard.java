package aoc.days;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoBoard {
    private static final Logger logger = LoggerFactory.getLogger(BingoBoard.class);
    private static final Pattern separate = Pattern.compile("\\s+");

    private final int[][] board;
    private boolean hasWon;

    BingoBoard(List<String> values) {
        if (values.size() > 5)
            throw new IllegalStateException("The board input is too large: '%s'".formatted(values));

        board = values.stream()
                .map(separate::split)
                .map(array -> Arrays.stream(array)
                        .filter(Predicate.not(String::isBlank))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    public long sumUnmarked() {
        return Arrays.stream(board).flatMapToInt(Arrays::stream).filter(i -> i > 0).sum();
    }

    public void mapPositions(BingoMap map) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                map.addPosition(board[row][col], new BoardPosition(this, row, col));
            }
        }
    }

    public void markPosition(BoardPosition position) {
        if (hasWon) return;

        if (this != position.board())
            throw new IllegalArgumentException("The position to be marked is not on this board");
        if (board[position.row()][position.col()] < 0)
            throw new IllegalArgumentException("The board position (%d,%d) has already been marked: %d"
                    .formatted(position.row(), position.col(), board[position.row()][position.col()]));


        if (board[position.row()][position.col()] == 0) {
            board[position.row()][position.col()] = -100;
        } else {
            board[position.row()][position.col()] *= -1;
        }
        checkWon(position);
    }


    private void checkWon(BoardPosition position) {
        hasWon = checkRow(position) || checkColumn(position);
    }

    private boolean checkRow(BoardPosition position) {
        return IntStream.range(0, 5)
                .map(i -> board[i][position.col()])
                .allMatch(i -> i < 0);
    }

    private boolean checkColumn(BoardPosition position) {
        return IntStream.range(0, 5)
                .map(i -> board[position.row()][i])
                .allMatch(i -> i < 0);
    }

    public boolean hasWon() {
        return hasWon;
    }

    @Override
    public String toString() {
        return Arrays.stream(board)
                .map(array -> Arrays.stream(array)
                        .mapToObj("%3d"::formatted)
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining("\n"));
    }
}
