package validation.impl;

import static validation.util.ValidatorUtil.genderCheck;
import static validation.util.ValidatorUtil.isPositive;
import static validation.util.ValidatorUtil.maxStringLength;
import static validation.util.ValidatorUtil.maxValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class HumanValidatorImpl implements Validator {

    private static final int MAX_AGE_VALUE = 100;
    private static final int MAX_NAME_LENGTH = 30;

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

        if (genderCheck(gender)) {
            throw new IncorrectDataTypeException(
                "Гендер можеть быть только Male или Female.");
        }

        if (isPositive(Age) || maxValue(Age, MAX_AGE_VALUE)) {
            throw new IncorrectAgeException(String.format(
                "Возраст не может быть больше %d.", MAX_AGE_VALUE));
        }

        if (maxStringLength(name, MAX_NAME_LENGTH)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Имя не должно превышать %d символов.", MAX_NAME_LENGTH));
        }
    }
}

