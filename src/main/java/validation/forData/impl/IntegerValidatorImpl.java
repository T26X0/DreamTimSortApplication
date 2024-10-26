package validation.forData.impl;

import validation.exception.IncorrectAgeException;
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
    public void validateMaxPossibleIntValue(int value, int maxPossible) throws NegativeNumberException, IncorrectAgeException {
        isPositive(value);
        if (value <= maxPossible) throw new IncorrectAgeException(String.format(
                "Возраст не может быть больше %d и меньше 0.", maxPossible));
    }
}
