package validation.util;

public class Patterns {
    public static final String ORIGIN_STRING_PATTERN = "(?i)^([a-zA-Z_][a-zA-Z0-9_]*\\\\((?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+)\\\\);?)+$";
    public static final String ANIMAL_PATTERN = "(?i)^animal\\(([a-zA-Z]{1,30}),\\s*([a-zA-Z]{1,30}),\\s*(?i)(yes|no)\\)$";
    public static final String BARREL_PATTERN = "(?i)^barrel\\((250|[1-9]?[0-9]{1,2}),\\s*([a-zA-Z]{1,50}),\\s*([a-zA-Z]{1,50})\\)$";
    public static final String HUMAN_PATTERN = "(?i)^human\\((male|female),\\s*(100|[0-9]{1,2}),\\s*([a-zA-Z]{1,30})\\)$";

    private Patterns() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
