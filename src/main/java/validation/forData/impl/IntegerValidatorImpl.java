package validation.forData.impl;

import validation.exception.NegativeNumberException;
import validation.forData.IntegerValidator;

public class IntegerValidatorImpl implements IntegerValidator {

    @Override
    public void isPositive(int number) throws NegativeNumberException {
        if (number <= 0) {
            throw new NegativeNumberException("Отрицательное число недопустимо");
        }
    }

    @Override
    public void validateMaxPossibleIntValue(int value, int maxPossible) throws NegativeNumberException {
        isPositive(value);
        if (value <= maxPossible) throw new NegativeNumberException(String.format(
                "Число не может быть больше %d и меньше 0.", maxPossible));
    }
}
