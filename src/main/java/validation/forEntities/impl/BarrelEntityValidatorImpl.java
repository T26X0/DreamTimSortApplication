package validation.forEntities.impl;

import static model.repository.constants.EntityConstants.MAX_VOLUME;
import static model.repository.constants.EntityPatternsRegex.BARREL_PATTERN;
import static model.repository.constants.EntityPatternsRegex.FIELD_PATTERN;

import java.util.ArrayList;
import java.util.List;
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

    private final int maxValue = Integer.parseInt(MAX_VOLUME.getValue());

    @Override
    public void validateEntityString(String barrel) throws PatternMismatchException, ExceedingPermissibleLengthException, NegativeNumberException, IncorrectAgeException {

        Pattern pattern = Pattern.compile(FIELD_PATTERN.getPattern());
        Matcher matcher = pattern.matcher(barrel);
        List<String> list = new ArrayList<>();
        if (matcher.find()) {
            list = List.of(matcher.group(1).split("\\s*,\\s*"));
        }
        int volume = Integer.parseInt(list.get(0));
        String storedMaterial = list.get(1);
        String materialOfManufacture = list.get(2);

        stringValidator.validateMaxStringLength(storedMaterial);
        stringValidator.validateMaxStringLength(materialOfManufacture);

        integerValidator.validateMaxPossibleIntValue(volume, maxValue); {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", maxValue));
        }
    }
}