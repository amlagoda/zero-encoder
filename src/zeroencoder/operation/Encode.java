package zeroencoder.operation;

import zeroencoder.util.Number;

import java.security.InvalidParameterException;
import java.util.StringJoiner;

public class Encode {
    public static String execute(
        String input
    ) throws InvalidParameterException {
        if (input == null) {
            throw new InvalidParameterException();
        }

        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sequence.append(Number.decimalToBinary(input.charAt(i)));
        }

        String binary = sequence.toString();
        int countSequences = 0;
        String last = "";

        for (int i = 0; i < binary.length(); i++) {
            String current = String.valueOf(binary.charAt(i));
            if (!last.equals(current)) {
                last = current;
                countSequences++;
            }
        }

        int[][] sequences = new int[countSequences][2];

        last = "";
        int index = -1;

        for (int i = 0; i < binary.length(); i++) {
            String current = String.valueOf(binary.charAt(i));
            if (!last.equals(current)) {
                last = current;
                index++;
                sequences[index][0] = Integer.parseInt(current);
            }
            sequences[index][1]++;
        }

        StringJoiner encrypt = new StringJoiner(" ");

        for (int[] seq : sequences) {
            StringBuilder encryptPart = new StringBuilder();
            encryptPart.append(seq[0] == 0 ? "00 " : "0 ");
            encryptPart.append("0".repeat(seq[1]));
            encrypt.add(encryptPart);
        }

        return encrypt.toString();
    }
}
