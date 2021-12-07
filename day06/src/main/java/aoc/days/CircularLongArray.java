package aoc.days;

import java.util.StringJoiner;
import java.util.stream.IntStream;

public class CircularLongArray {
    private final long[] array;
    private int position;

    public CircularLongArray(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Unable to create an array with negative or zero capacity: " + capacity);
        array = new long[capacity];
        position = 0;
    }

    public CircularLongArray(long[] initialValues) {
        array = initialValues;
        position = 0;
    }

    public long pop() {
        long value = peek();
        set(0, 0);
        incrementPosition();
        return value;
    }

    public void advance() {
        incrementPosition();
    }

    public long peek() {
        return get(0);
    }

    public long get(int index) {
        return array[adjustedIndex(index)];
    }

    public void set(int index, long value) {
        array[adjustedIndex(index)] = value;
    }

    public void add(int index, long value) {
        set(index, value + get(index));
    }

    public long[] toArray() {
        return IntStream.range(0, array.length)
                .mapToLong(this::get)
                .toArray();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "CircArray[", "]");
        IntStream.range(0, array.length)
                .forEach(i -> joiner.add(Long.toString(get(i))));
        return joiner.toString();
    }

    private void incrementPosition() {
        position = (position + 1) % array.length;
    }

    private int adjustedIndex(int index) {
        checkBounds(index);
        return (position + index) % array.length;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= array.length) throw new ArrayIndexOutOfBoundsException(
                "Index '%d' is out of bounds for array with capacity %d".formatted(index, array.length));
    }
}