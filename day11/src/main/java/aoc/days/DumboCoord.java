package aoc.days;

import aoc.utils.Coord;
import org.jetbrains.annotations.NotNull;

public record DumboCoord(@NotNull Dumbo dumbo, @NotNull Coord location) {

    public boolean canFlash() {
        return dumbo.canFlash();
    }

    public boolean hasFlashed() {
        return dumbo.hasFlashed();
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DumboCoord that = (DumboCoord) o;

        return location.equals(that.location);
    }

    @Override public int hashCode() {
        return location.hashCode();
    }
}
