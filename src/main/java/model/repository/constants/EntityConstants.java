package model.repository.constants;

import lombok.Getter;

@Getter
public enum EntityConstants {

    MAX_STRING_LENGTH("50"),
    MAX_AGE("100"),
    MAX_VOLUME("250"),
    GENDER_MALE("male"),
    GENDER_FEMALE("female"),
    HAS_WOOL("yes"),
    HAS_NO_WOOL("no");

    private final String value;

    EntityConstants(String value) {
        this.value = value;
    }

}