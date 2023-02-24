package zeroencoder.operation;

import zeroencoder.util.Array;
import zeroencoder.util.Number;

import java.security.InvalidParameterException;

public class Decode {
    public static String execute(
        String input
    ) throws InvalidParameterException, NumberFormatException {
        if (input == null) {
            throw new InvalidParameterException();
        }

        String[] sequences = input.split(" ");
        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < sequences.length; i += 2) {
            String fill = sequences[i].equals("0") ? "1" : "0";
            int length = sequences[i + 1].length();
            binary.append(fill.repeat(length));
        }

        StringBuilder decoded = new StringBuilder();
        String[] numbers = binary.toString().split("");

        for (int i = 0; i < numbers.length; i += 7) {
            String[] parts = Array.slice(numbers, i, i + 6);
            StringBuilder binaryNumber = new StringBuilder();

            for (int j = 0; j < parts.length; j++) {
                binaryNumber.append(parts[j]);
            }

            int decimalNumber = Number.binaryToDecimal(binaryNumber.toString());
            decoded.append((char)decimalNumber);
        }

        return decoded.toString();
    }
}
