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

    @Override
    public void validate(String human)
        throws ExceedingPermissibleLengthException, IncorrectAgeException, PatternMismatchException, IncorrectDataTypeException {

        Pattern pattern = Pattern.compile(Patterns.HUMAN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        String gender = matcher.group(1);
        int Age = Integer.parseInt(matcher.group(2));
        String name = matcher.group(3);

        if (genderCheck(gender)) {
            throw new IncorrectDataTypeException("Гендер можеть быть только Male или Female.");
        }
        if (isPositive(Age) || maxValue(Age, 100)) {
            throw new IncorrectAgeException("Возраст не может быть больше 100.");
        }
        if (maxStringLength(name, 30)) {
            throw new ExceedingPermissibleLengthException("Имя не должно превышать 30 символов.");
        }
    }
}

