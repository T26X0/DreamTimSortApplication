package validation.impl;

import static model.repository.constants.EntityConstants.*;
import static model.repository.constants.EntityPatternsRegex.HUMAN_PATTERN;
import static validation.util.ValidatorUtil.validateMaxPossibleIntValue;
import static validation.util.ValidatorUtil.validateMaxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;

public class HumanValidatorImpl implements Validator {

    private final int maxAge = Integer.parseInt(MAX_AGE.getValue());

    private final String genderMale = GENDER_MALE.getValue();

    private final String genderFemale = GENDER_FEMALE.getValue();

    @Override
    public void validate(String human)
        throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(HUMAN_PATTERN.getPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String gender = matcher.group(1);
        int Age = Integer.parseInt(matcher.group(2));
        String name = matcher.group(3);

        validateMaxStringLength(name, "Имя");

        if (!validateMaxPossibleIntValue(Age, maxAge)) {
            throw new IncorrectAgeException(String.format(
                "Возраст не может быть больше %d и меньше 0.", maxAge));
        }

        if (!gender.equalsIgnoreCase(genderMale) && !gender.equalsIgnoreCase(genderFemale)) {
            throw new IncorrectDataTypeException(String.format(
                "Гендер можеть быть только '%s' или '%s'.", genderMale, genderFemale));
        }
    }
}

