package validation.forData;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.NegativeNumberException;

public interface IntegerValidator {

    void isPositive(int number) throws Exception;

    void validateMaxPossibleIntValue(int value, int maxPossible) throws ExceedingPermissibleLengthException, NegativeNumberException, IncorrectAgeException;
}