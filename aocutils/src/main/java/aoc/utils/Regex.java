package aoc.utils;

import java.util.regex.Pattern;

public enum Regex {
    ;

    private static final Pattern singleChars = Pattern.compile("");
    private static final Pattern whiteSpace = Pattern.compile("\\s+");

    public static String[] splitIntoSingleChars(String string) {
        return singleChars.split(string);
    }

    public static String[] splitAtWhiteSpace(String string) {
        return whiteSpace.split(string);
    }
}
