package aoc.testing;

import java.util.stream.Stream;

public interface Tester {
    Stream<String> getInput();
    void testExamples();
}
