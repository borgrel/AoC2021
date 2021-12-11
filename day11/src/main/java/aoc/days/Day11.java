package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Day11 extends AbstractDay {
    private static final Logger logger = LoggerFactory.getLogger(Day11.class);

    private DumboArray dumbos;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        dumbos = new DumboArray(stream);
    }

    private int propagateFlash(DumboCoord dumbo) {
        if (!dumbo.canFlash()) return 0;

        dumbo.dumbo().flash();
        return 1 + dumbos.getNeighbours(dumbo)
                .map(neighbour -> {
                    neighbour.dumbo().gainEnergy();
                    return neighbour;
                })
                .mapToInt(this::propagateFlash)
                .sum();
    }

    private int cycle() {
        int flashes = dumbos.stream()
                .map(dumbo -> {
                    dumbo.dumbo().gainEnergy();
                    return dumbo;
                })
                .mapToInt(this::propagateFlash)
                .sum();

        dumbos.stream()
                .map(DumboCoord::dumbo)
                .forEach(Dumbo::reset);
        return flashes;
    }

    @Override //1637
    public void part1() {
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            flashes += cycle();
            logger.debug("Cycle {}:\n{}", i, dumbos);
        }
        result1 = Integer.toString(flashes);
    }

    @Override //242
    public void part2() {
        int flashes = 0;
        int cycles;
        for (cycles = 100; flashes < 100; cycles++) {
            flashes = cycle();
        }
        result2 = Integer.toString(cycles);
    }
}
