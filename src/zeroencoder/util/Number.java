package zeroencoder.util;

import java.security.InvalidParameterException;

public class Number {
    public static String decimalToBinary(int decimalNumber) {
        StringBuilder binary = new StringBuilder();

        do {
            binary.append(decimalNumber % 2);
            decimalNumber = decimalNumber / 2;
        } while (decimalNumber > 0);

        while (binary.length() < 7) {
            binary.append(0);
        }

        return binary.reverse().toString();
    }

    public static int binaryToDecimal(
        String binaryNumber
    ) throws InvalidParameterException, NumberFormatException {
        if (binaryNumber == null || "".equals(binaryNumber)) {
            throw new InvalidParameterException();
        }

        int pow = 0;
        int sum = 0;

        for (int i = binaryNumber.length() - 1; i > -1; i--) {
            int num = Integer.parseInt(
                String.valueOf(binaryNumber.charAt(i))
            );
            sum += num * Math.pow(2, pow);
            pow++;
        }

        return sum;
    }
}
