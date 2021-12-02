package aoc.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.Stream;

public final class StreamIterable<T> implements Iterable<T> {

    private final Stream<T> stream;

    private StreamIterable(Stream<T> stream) {
        this.stream = stream;
    }

    public static <T> StreamIterable<T> of(Stream<T> stream) {
        return new StreamIterable<>(stream);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return stream.iterator();
    }
}
