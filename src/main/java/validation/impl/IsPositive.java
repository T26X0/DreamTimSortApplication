package validation.impl;

public class IsPositive {

    public static void validate(int value) throws Exception {

        if (value <= 0) {
            throw new Exception("Отрицательное число недопустимо");
        }
    }
}
