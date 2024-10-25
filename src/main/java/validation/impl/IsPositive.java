package validation.impl;

public class IsPositive {

    public static void validate(int value) {

        if (value <= 0) {
            throw new IllegalArgumentException("Отрицательное число недопустимо");
        }
    }
}
