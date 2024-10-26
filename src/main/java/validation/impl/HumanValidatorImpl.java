package validation.impl;

import static model.repository.constants.ValidationConstants.EntityConstants.GENDER_FEMALE;
import static model.repository.constants.ValidationConstants.EntityConstants.GENDER_MALE;
import static model.repository.constants.ValidationConstants.EntityConstants.MAX_AGE;
import static model.repository.constants.ValidationConstants.RegexPatterns.HUMAN_PATTERN;
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

    @Override
    public void validate(String human)
        throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(HUMAN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String gender = matcher.group(1);
        int Age = Integer.parseInt(matcher.group(2));
        String name = matcher.group(3);

        validateMaxStringLength(name, "Имя");

        if (!validateMaxPossibleIntValue(Age, MAX_AGE)) {
            throw new IncorrectAgeException(String.format(
                "Возраст не может быть больше %d и меньше 0.", MAX_AGE));
        }

        if (!gender.equalsIgnoreCase(GENDER_MALE) && !gender.equalsIgnoreCase(GENDER_FEMALE)) {
            throw new IncorrectDataTypeException(String.format(
                "Гендер можеть быть только '%s' или '%s'.", GENDER_MALE, GENDER_FEMALE));
        }
    }
}

