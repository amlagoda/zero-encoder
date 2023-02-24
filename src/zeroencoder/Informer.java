package zeroencoder;

import zeroencoder.operation.OperationType;

import java.util.StringJoiner;

class Informer {
    static String choose() {
        StringJoiner operations = new StringJoiner("/");

        for (OperationType operationType : OperationType.values()) {
            operations.add(operationType.name().toLowerCase());
        }

        return String.format("Please input operation (%s):", operations);
    }

    static String unknown(String operation) {
        if (operation == null) {
            return "Operation not found";
        }

        return String.format("There is no '%s' operation", operation);
    }

    static String in(OperationType operation) {
        if (operation == OperationType.DECODE) {
            return "Input encoded string:";
        }

        return "Input string:";
    }

    static String out(OperationType operation) {
        if (operation == OperationType.DECODE) {
            return "Decoded string:";
        }

        return "Encoded string:";
    }

    static String invalid(OperationType operation) {
        if (operation == OperationType.DECODE) {
            return "Encoded string is not valid.";
        }

        return "Input string is not valid.";
    }

    static String bye() {
        return "Bye!";
    }

    static String fail() {
        return "Something went wrong. Try again";
    }
}
