package aoc.runner;

public enum Days {
    DAY01,
    DAY02,
    DAY03,
    DAY04,
    DAY05,
    DAY06,
    DAY07,
    DAY08,
    DAY09,
    DAY10,
    DAY11,
    DAY12,
    DAY13,
    DAY14,
    DAY15,
    DAY16,
    DAY17,
    DAY18,
    DAY19,
    DAY20,
    DAY21,
    DAY22,
    DAY23,
    DAY24,
    DAY25,
    OUTPUT;

    private static final String PACKAGE_NAME = "aoc.days";

    public static Days dayFromInt(int value) {
        if (value < 0 || value > Days.values().length) {
            throw new IllegalArgumentException("This enum: '%s' only has %d elements, %d is an illegal value"
                    .formatted(Days.class.getSimpleName(), Days.values().length, value));
        }
        return Days.values()[value - 1];
    }

    public String upperCaseFirstLetter() {
        return name().toLowerCase().replaceFirst("d", "D");
    }

    public String getFileName() {
        return "/%s.txt".formatted(name().toLowerCase());
    }

    public String getFileNameWithPackage() {
        return "/%s%s".formatted(PACKAGE_NAME.replace(".", "/"), getFileName());
    }

    public String getClassName() {
        return "%s.%s".formatted(PACKAGE_NAME, upperCaseFirstLetter());
    }
}
