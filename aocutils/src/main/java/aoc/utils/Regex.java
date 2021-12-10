package aoc.utils;

import java.util.regex.Pattern;

public enum Regex {
    ;

    private static final Pattern singleChars = Pattern.compile("");
    private static final Pattern whiteSpace = Pattern.compile("\\s+");
    private static final Pattern newLine = Pattern.compile("\n");
    private static final Pattern commas = Pattern.compile(",");

    public static String[] splitIntoSingleChars(String string) {
        return singleChars.split(string);
    }

    public static String[] splitAtWhiteSpace(String string) {
        return whiteSpace.split(string);
    }

    public static String[] splitAtCommas(String string) {
        return commas.split(string);
    }

    public static String[] splitAtNewLine(String string) {
        return newLine.split(string);
    }
}
