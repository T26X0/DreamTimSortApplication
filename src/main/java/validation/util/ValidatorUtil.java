package validation.util;


import model.repository.constants.EntityConstants;
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
        int maxStringLength = Integer.parseInt(EntityConstants.MAX_STRING_LENGTH.getValue());

        if(string.length() > maxStringLength) {
            throw new ExceedingPermissibleLengthException(String.format(
                "%s не должен превышать %d символов.", fieldName, maxStringLength));
        }
    }
}
