package model.repository.constants;

public interface ValidationConstants {
    interface RegexPatterns {
        String SOURCE_STRING_PATTERN = "(?i)^\\s*(?:[a-zA-Z][a-zA-Z0-9]*\\(\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*,\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*,\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*\\);?\\s*)+$";
        String ANIMAL_PATTERN = "(?i)^animal\\s*\\(\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+)\\s*\\)\\s*;\\s*$";
        String BARREL_PATTERN = "(?i)^barrel\\s*\\(\\s*(\\d{1,3})\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*\\)\\s*;\\s*$";
        String HUMAN_PATTERN = "(?i)^human\\s*\\(\\s*([a-zA-Z]+)\\s*,\\s*(\\d{1,3})\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*\\)\\s*;\\s*$";
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
