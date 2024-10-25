package validation.util;

import static validation.util.ValidationConstants.EntityConstants.MAX_STRING_LENGTH;

import validation.exception.ExceedingPermissibleLengthException;

public interface ValidatorUtil {

    static boolean isPositive(int number) {
        return number >= 0;
    }

    static boolean validateMaxPossibleIntValue(int value, int maxPossible) {
        return isPositive(value) && value <= maxPossible;
    }

    static void validateMaxStringLength(String string, String fieldName)
        throws ExceedingPermissibleLengthException {
        if(string.length() > MAX_STRING_LENGTH) {
            throw new ExceedingPermissibleLengthException(String.format(
                "%s не должен превышать %d символов.", fieldName, MAX_STRING_LENGTH));
        }
    }
}
