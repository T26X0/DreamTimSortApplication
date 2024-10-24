package validation.util;

public class Patterns {

    public static final String SOURCE_STRING_PATTERN = "(?i)^([a-zA-Z][a-zA-Z0-9]*\\((?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+)\\);?)+$";
    public static final String ANIMAL_PATTERN = "(?i)^animal\\(([a-zA-Z]+),\\s*([a-zA-Z]+),\\s*([a-zA-Z]+)\\)$";
    public static final String BARREL_PATTERN = "(?i)^barrel\\((\\d{1,3}),\\s*([a-zA-Z]+),\\s*([a-zA-Z]+)\\)$";
    public static final String HUMAN_PATTERN = "(?i)^human\\(([a-zA-Z]+),\\s*(\\d{1,3}),\\s*([a-zA-Z])\\)$";

    private Patterns() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
