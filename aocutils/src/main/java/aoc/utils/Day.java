package aoc.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface Day {
    void convertInput(@NotNull Stream<String> stream);
    void part1();
    void part2();

    @NotNull Optional<String> result1();
    @NotNull Optional<String> result2();
}
