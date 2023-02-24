package zeroencoder.util;

import java.util.Objects;

public class Array {
    public static String[] slice(String[] array, int startIndex, int endIndex) {
        boolean hasWrongParams = array == null ||
                array.length == 0 ||
                startIndex < 0 ||
                startIndex >= array.length ||
                startIndex > endIndex ||
                endIndex >= array.length;

        if (hasWrongParams) {
            return new String[0];
        }

        int length = array.length - startIndex - (array.length - endIndex - 1);
        String[] slice = new String[length];

        for (int i = startIndex; i <= endIndex; i++) {
            slice[i - startIndex] = array[i];
        }

        return slice;
    }

    public static boolean has(String[] array, String needle) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return false;
        }

        for (String value : array) {
            if (Objects.equals(value, needle)) {
                return true;
            }
        }

        return false;
    }
}
