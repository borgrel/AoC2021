package aoc.days;

public class Dumbo {
    private static final int FLASH_REQUIREMENT = 9;

    int energy;
    boolean hasFlashed;

    Dumbo(int energy) {
        this.energy = energy;
        hasFlashed = false;
    }

    boolean hasFlashed() {
        return hasFlashed;
    }

    boolean canFlash() {
        return !hasFlashed && energy > FLASH_REQUIREMENT;
    }

    public void gainEnergy() {
        energy++;
    }

    public void flash() {
        if (energy < FLASH_REQUIREMENT)
            throw new IllegalStateException("Unable to flash with only '%d' energy".formatted(energy));
        hasFlashed = true;
    }

    public void reset() {
        if (hasFlashed) {
            hasFlashed = false;
            energy = 0;
        }
    }

    public int energy() {
        return energy;
    }
}
