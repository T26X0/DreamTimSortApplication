package validation.util;

import static validation.util.ValidationConstants.GENDER_FEMALE;
import static validation.util.ValidationConstants.GENDER_MALE;
import static validation.util.ValidationConstants.HAS_HAIR;
import static validation.util.ValidationConstants.HAS_NO_HAIR;
import static validation.util.ValidationConstants.MAX_STRING_LENGTH;

public class ValidatorUtil {

    public static boolean isPositive(int number) {
        return number > 0;
    }

    public static boolean compareValueWithMaxPossible(int value, int max) {
        return value <= max;
    }

    public static boolean matchesMaxStringLength(String string) {
        return string.length() < MAX_STRING_LENGTH;
    }

    public static boolean genderCheck(String gender) {
        return gender.equalsIgnoreCase(GENDER_MALE) || gender.equalsIgnoreCase(GENDER_FEMALE);
    }

    public static boolean hairCheck(String hair) {
        return hair.equalsIgnoreCase(HAS_HAIR) || hair.equalsIgnoreCase(HAS_NO_HAIR);
    }

    private ValidatorUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
