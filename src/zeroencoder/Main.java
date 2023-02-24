package zeroencoder;

import zeroencoder.operation.Decode;
import zeroencoder.operation.Encode;
import zeroencoder.operation.OperationType;
import zeroencoder.validation.Error;
import zeroencoder.validation.Validator;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(Informer.choose());
            String input = scanner.nextLine();

            OperationType operation = Validator.validatedOperation(input);
            if (operation == null) {
                System.out.println(Informer.unknown(input));
                continue;
            }

            if (operation == OperationType.EXIT) {
                System.out.println(Informer.bye());
                return;
            }

            System.out.println(Informer.in(operation));
            input = scanner.nextLine();

            Error[] errors = Validator.validate(input, operation);
            if (errors.length > 0) {
                System.out.println(Informer.invalid(operation));
                continue;
            }

            System.out.println(Informer.out(operation));

            if (operation == OperationType.ENCODE) {
                System.out.println(Encode.execute(input));
                continue;
            }

            try {
                System.out.println(Decode.execute(input));
            } catch (Exception e) {
                System.out.println(Informer.fail());
            }
        } while (true);
    }
}
