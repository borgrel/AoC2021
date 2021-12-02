package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day02 extends AbstractDay {
    private static final Map<String, Direction> directionMapping =
            Map.of("forward", Direction.FORWARD, "up", Direction.UP, "down", Direction.DOWN);

    List<Instruction> instructions;

    public Instruction parseLine(String input) {
        String[] split = input.split(" ");
        Direction direction = directionMapping.get(split[0]);
        if (direction == null) throw new IllegalStateException("Input file includes invalid entry: '%s'".formatted(split[0]));
        int value = Integer.parseInt(split[1]);
        return new Instruction(direction,value);
    }

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        instructions = stream.map(this::parseLine)
                .toList();
    }

    @Override
    public void part1() {
        Map<Direction, Long> result = new EnumMap<>(Direction.class);
        directionMapping.values()
                .stream()
                .forEach(direction -> result.put(direction, 0L));
        instructions.stream()
                .forEach(instruction -> result.compute(instruction.direction(),
                        (direction, mapValue) -> mapValue + instruction.value()));
        long x = result.get(Direction.FORWARD);
        long y = result.get(Direction.DOWN) - result.get(Direction.UP);
        result1 = Long.toString(x*y);
    }

    @Override
    public void part2() {
        Map<Direction, Long> result = new EnumMap<>(Direction.class);
        directionMapping.values()
                .stream()
                .forEach(direction -> result.put(direction, 0L));
        instructions.stream()
                .map(Instruction::adjustAim)
                .filter(instruction -> instruction.direction().equals(Direction.FORWARD))
                .forEach(instruction -> {
                    result.compute(instruction.direction(),
                            (direction, mapValue) -> mapValue + instruction.value());
                    result.compute(Direction.DOWN,
                            (direction, mapValue) -> mapValue + (long) instruction.value() * Instruction.getAim());
                });
        long x = result.get(Direction.FORWARD);
        long y = result.get(Direction.DOWN);
        result2 = Long.toString(x*y);
    }
}
