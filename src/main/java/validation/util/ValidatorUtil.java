package validation.util;

import static validation.util.ValidationConstants.GENDER_FEMALE;
import static validation.util.ValidationConstants.GENDER_MALE;
import static validation.util.ValidationConstants.HAS_HAIR;
import static validation.util.ValidationConstants.HAS_NO_HAIR;
import static validation.util.ValidationConstants.MAX_STRING_LENGTH;

public class ValidatorUtil {

    public static boolean isPositive(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static boolean compareValueWithMaxPossible(int value, int max) {
        if (value > max) {
            return false;
        }
        return true;
    }

    public static boolean matchesMaxStringLength(String string) {
        if (string.length() < MAX_STRING_LENGTH) {
            return false;
        }
        return true;
    }

    public static boolean genderCheck(String gender) {
        if (!gender.equalsIgnoreCase(GENDER_MALE) && !gender.equalsIgnoreCase(GENDER_FEMALE)) {
            return false;
        }
        return true;
    }

    public static boolean hairCheck(String hair) {
        if (!hair.equalsIgnoreCase(HAS_HAIR) && !hair.equalsIgnoreCase(HAS_NO_HAIR)) {
            return false;
        }
        return true;
    }

    private ValidatorUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
