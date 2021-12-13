package aoc.days;

import aoc.utils.Direction;

public record FoldInstruction(Direction direction, int location) {
    private static final String PREFIX = "fold along ";

    public static FoldInstruction fromString(String input) {
        if (!input.startsWith(PREFIX))
            throw new IllegalArgumentException("The string to create an Instruction from does not start with 'fold along '! " + input);

        Direction direction = switch (input.charAt(PREFIX.length())) {
            case 'y' -> Direction.UP;
            case 'x' -> Direction.RIGHT;
            default -> throw new IllegalArgumentException("The string to create an Instruction from does not include an 'x' or 'y'! " + input);
        };
        int location = Integer.parseInt(input.substring(PREFIX.length() + 2));

        return new FoldInstruction(direction, location);
    }
}
