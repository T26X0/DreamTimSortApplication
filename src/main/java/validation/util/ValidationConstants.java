package validation.util;

public interface ValidationConstants {
    interface RegexPatterns {
        String SOURCE_STRING_PATTERN = "(?i)^(?:[a-zA-Z][a-zA-Z0-9]*\\((?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+),(?:[a-zA-Z]+|[0-9]+)\\);?\\s*)+$";
        String ANIMAL_PATTERN = "(?i)^animal\\(([a-zA-Z]+),\\s*([a-zA-Z]+),\\s*([a-zA-Z]+)\\)$";
        String BARREL_PATTERN = "(?i)^barrel\\((\\d{1,3}),\\s*([a-zA-Z]+),\\s*([a-zA-Z]+)\\)$";
        String HUMAN_PATTERN = "(?i)^human\\(([a-zA-Z]+),\\s*(\\d{1,3}),\\s*([a-zA-Z]+)\\)$";
    }
    interface EntityConstants {
        int MAX_STRING_LENGTH = 50;
        int MAX_AGE = 100;
        int MAX_VOLUME = 250;
        String GENDER_MALE = "male";
        String GENDER_FEMALE = "female";
        String HAS_WOOL = "yes";
        String HAS_NO_WOOL = "no";
    }
}
