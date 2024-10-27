package validation.forData;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.NegativeNumberException;

public interface IntegerValidator {

    void isPositive(int number) throws NegativeNumberException ;

    void validateMaxPossibleIntValue(int value, int maxPossible) throws ExceedingPermissibleLengthException, NegativeNumberException;
}