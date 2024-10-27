package validation.forData;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.NegativeNumberException;
import validation.exception.PatternMismatchException;

public interface DataValidator {

    void isPositive(int number) throws NegativeNumberException;
    void validateMaxPossibleIntValue(int value, int maxPossible) throws ExceedingPermissibleLengthException, NegativeNumberException;
    void validateSourceString(String source) throws PatternMismatchException;
    void validateMaxStringLength(String string) throws ExceedingPermissibleLengthException;
}
