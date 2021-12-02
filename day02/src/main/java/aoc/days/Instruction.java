package aoc.days;

import aoc.utils.Direction;

public record Instruction(Direction direction, int value) {
    private static int aim = 0;

    public static Instruction adjustAim(Instruction instruction) {
        aim += switch (instruction.direction) {
            case UP -> -1 * instruction.value();
            case DOWN -> instruction.value();
            case FORWARD -> 0;
            default -> throw new IllegalStateException("Instruction includes illegal direction: '%s'".formatted(instruction.direction));
        };
        return instruction;
    }
    public static int getAim() {
        return aim;
    }
}
