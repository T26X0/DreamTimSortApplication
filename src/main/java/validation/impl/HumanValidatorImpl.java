package validation.impl;

import static validation.util.ValidationConstants.GENDER_FEMALE;
import static validation.util.ValidationConstants.GENDER_MALE;
import static validation.util.ValidationConstants.MAX_AGE;
import static validation.util.ValidationConstants.MAX_STRING_LENGTH;
import static validation.util.ValidatorUtil.genderCheck;
import static validation.util.ValidatorUtil.isPositive;
import static validation.util.ValidatorUtil.matchesMaxStringLength;
import static validation.util.ValidatorUtil.compareValueWithMaxPossible;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class HumanValidatorImpl implements Validator {

    @Override
    public void validate(String human)
        throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(Patterns.HUMAN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String gender = matcher.group(1);
        int Age = Integer.parseInt(matcher.group(2));
        String name = matcher.group(3);

        if (!genderCheck(gender)) {
            throw new IncorrectDataTypeException(String.format(
                "Гендер можеть быть только '%s' или '%s'.", GENDER_MALE, GENDER_FEMALE));
        }

        if (!isPositive(Age) || !compareValueWithMaxPossible(Age, MAX_AGE)) {
            throw new IncorrectAgeException(String.format(
                "Возраст не может быть больше %d и меньше 0.", MAX_AGE));
        }

        if (!matchesMaxStringLength(name)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Имя не должно превышать %d символов.", MAX_STRING_LENGTH));
        }
    }
}

