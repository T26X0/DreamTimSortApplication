package validation.forEntities.impl;

import static model.repository.constants.EntityConstants.MAX_VOLUME;
import static model.repository.constants.EntityPatternsRegex.BARREL_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.forEntities.BarrelEntityValidator;
import validation.exception.IncorrectAgeException;
import validation.exception.NegativeNumberException;
import validation.forData.impl.IntegerValidatorImpl;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.PatternMismatchException;
import validation.forData.impl.StringValidatorImpl;
import validation.forData.IntegerValidator;
import validation.forData.StringValidator;

public class BarrelEntityValidatorImpl implements BarrelEntityValidator {

    StringValidator stringValidator = new StringValidatorImpl();
    IntegerValidator integerValidator = new IntegerValidatorImpl();

    private final String maxValue = MAX_VOLUME.getValue();

    @Override
    public void validateEntityString(String barrel) throws PatternMismatchException, ExceedingPermissibleLengthException, NegativeNumberException, IncorrectAgeException {

        Pattern pattern = Pattern.compile(BARREL_PATTERN.getPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        int volume = Integer.parseInt(matcher.group(1));
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        stringValidator.validateMaxStringLength(storedMaterial);
        stringValidator.validateMaxStringLength(materialOfManufacture);

        integerValidator.validateMaxPossibleIntValue(volume, Integer.parseInt(maxValue)); {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", Integer.parseInt(maxValue)));
        }
    }
}