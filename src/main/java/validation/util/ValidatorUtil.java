package validation.util;

public class ValidatorUtil {

    public static boolean isPositive(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static boolean maxValue(int value, int max) {
        if (value > max) {
            return false;
        }
        return true;
    }

    public static boolean maxStringLength(String string, int length) {
        if (string.length() > length) {
            return false;
        }
        return true;
    }

    public static boolean genderCheck(String gender) {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            return false;
        }
        return true;
    }

    public static boolean hairCheck(String hair) {
        if (!hair.equalsIgnoreCase("yes") && !hair.equalsIgnoreCase("no")) {
            return false;
        }
        return true;
    }

    private ValidatorUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
