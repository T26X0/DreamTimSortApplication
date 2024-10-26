package model.repository.constants;

public enum EntityPatternsRegex {
    SOURCE_STRING_PATTERN("(?i)^\\s*(?:[a-zA-Z][a-zA-Z0-9]*\\(\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*,\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*,\\s*(?:[a-zA-Z0-9-]+|[0-9-]+)\\s*\\);?\\s*)+$"),
    ANIMAL_PATTERN("(?i)^animal\\s*\\(\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+)\\s*\\)\\s*;\\s*$"),
    BARREL_PATTERN("(?i)^barrel\\s*\\(\\s*(\\d{1,3})\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*\\)\\s*;\\s*$"),
    HUMAN_PATTERN("(?i)^human\\s*\\(\\s*([a-zA-Z]+)\\s*,\\s*(\\d{1,3})\\s*,\\s*([a-zA-Z]+(?:-[a-zA-Z]+)*)\\s*\\)\\s*;\\s*$");

    private final String pattern;

    EntityPatternsRegex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
