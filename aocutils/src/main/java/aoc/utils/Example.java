package aoc.utils;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface Example {
    @NotNull Stream<String> sampleInput();

    @NotNull String sampleResultPart1();
    @NotNull String sampleResultPart2();
}
