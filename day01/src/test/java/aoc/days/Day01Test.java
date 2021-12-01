package aoc.days;

import aoc.testing.Tester;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class Day01Test /*implements Tester*/ {
    static String testInput = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """;

    //@Override
    static Stream<String> getInput() {
        return Arrays.stream(testInput.split("\n"));
    }


    //@Override
    @Test
    static void testExamples() {
        Day01 day = new Day01();
        day.convertInput(getInput());
        day.part1();
        day.part2();
        //assertEquals(Optional.of("7"), day.result1());
        System.out.println(day.result1());
        System.out.println(day.result2());
    }
    public static void main(String[] args) {
        testExamples();
    }
}