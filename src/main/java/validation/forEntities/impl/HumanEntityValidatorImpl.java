package validation.forEntities.impl;

import static model.repository.constants.EntityConstants.*;
import static model.repository.constants.EntityPatternsRegex.HUMAN_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.forEntities.HumanEntityValidator;
import validation.exception.*;
import validation.forData.impl.IntegerValidatorImpl;
import validation.forData.impl.StringValidatorImpl;
import validation.forData.IntegerValidator;
import validation.forData.StringValidator;

public class HumanEntityValidatorImpl implements HumanEntityValidator {

    StringValidator stringValidator = new StringValidatorImpl();
    IntegerValidator integerValidator = new IntegerValidatorImpl();

    private final int maxAge = Integer.parseInt(MAX_AGE.getValue());

    private final String genderMale = GENDER_MALE.getValue();

    private final String genderFemale = GENDER_FEMALE.getValue();

    @Override
    public void validateEntityString(String human)
            throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException, PatternMismatchException, NegativeNumberException {

        Pattern pattern = Pattern.compile(HUMAN_PATTERN.getPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String gender = matcher.group(1);
        int Age = Integer.parseInt(matcher.group(2));
        String name = matcher.group(3);

        stringValidator.validateMaxStringLength(name);

        integerValidator.validateMaxPossibleIntValue(Age, maxAge);

        if (!gender.equalsIgnoreCase(genderMale) && !gender.equalsIgnoreCase(genderFemale)) {
            throw new IncorrectDataTypeException(String.format(
                "Гендер можеть быть только '%s' или '%s'.", genderMale, genderFemale));
        }
    }
}

