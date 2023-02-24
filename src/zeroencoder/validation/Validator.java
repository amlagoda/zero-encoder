package zeroencoder.validation;

import zeroencoder.operation.OperationType;
import zeroencoder.util.Array;

public class Validator {
    public static OperationType validatedOperation(String input) {
        if (input == null) {
            return null;
        }

        try {
            return OperationType.valueOf(input.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    public static Error[] validate(String input, OperationType operationType) {
        if (input == null || operationType == null) {
            return new Error[]{Error.INVALID_PARAMS};
        }

        if ("".equals(input)) {
            return new Error[]{Error.EMPTY_INPUT};
        }

        if (operationType == OperationType.DECODE) {
            return validateDecode(input);
        }

        return new Error[0];
    }

    private static Error[] validateDecode(String input) {
        if (input == null) {
            return new Error[]{Error.INVALID_PARAMS};
        }

        if ("".equals(input)) {
            return new Error[]{Error.EMPTY_INPUT};
        }

        String[] symbols = input.split("");

        boolean hasZero = false;
        boolean hasWhitespace = false;

        for (int i = 0; i < symbols.length; i++) {
            if (!Array.has(new String[]{"0", " "}, symbols[i])) {
                return new Error[]{Error.CONTAINS_INVALID_CHARACTERS};
            }
            if ("0".equals(symbols[i])) {
                hasZero = true;
            }
            if (" ".equals(symbols[i])) {
                hasWhitespace = true;
            }
        }

        if (!hasZero || !hasWhitespace) {
            return new Error[]{Error.NOT_CONTAINS_CORRECT_COMBINATION};
        }

        String[] sequences = input.split(" ");
        if (sequences.length % 2 != 0) {
            return new Error[]{Error.NOT_ODD_COUNT_PARTS};
        }

        String[][] unaryPairs = new String[sequences.length / 2][2];
        int key = 0;

        for (int i = 0; i < sequences.length; i += 2) {
            if (!Array.has(new String[]{"0", "00"}, sequences[i])) {
                return new Error[]{Error.INVALID_ENCODED_VALUE};
            }

            String fill = "0".repeat(Math.max(0, sequences[i + 1].length()));
            if (!fill.equals(sequences[i + 1])) {
                return new Error[]{Error.INVALID_ENCODED_VALUE_FILL};
            }

            unaryPairs[key][0] = sequences[i];
            unaryPairs[key][1] = sequences[i + 1];
            key++;
        }

        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < unaryPairs.length; i++) {
            String value = "0".equals(unaryPairs[i][0]) ? "1" : "0";
            int length = unaryPairs[i][1].length();
            binary.append(value.repeat(length));
        }

        if (binary.toString().length() % 7 != 0) {
            return new Error[]{Error.CONTAINS_INVALID_BINARY};
        }

        return new Error[0];
    }
}
