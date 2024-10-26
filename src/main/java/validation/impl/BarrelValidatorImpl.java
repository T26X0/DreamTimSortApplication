package validation.impl;

import static model.repository.constants.EntityConstants.MAX_VOLUME;
import static model.repository.constants.EntityPatternsRegex.BARREL_PATTERN;
import static validation.util.ValidatorUtil.validateMaxPossibleIntValue;
import static validation.util.ValidatorUtil.validateMaxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.PatternMismatchException;

public class BarrelValidatorImpl implements Validator {

    private final String maxValue = MAX_VOLUME.getValue();

    @Override
    public void validate(String barrel)
        throws ExceedingPermissibleLengthException, PatternMismatchException {

        Pattern pattern = Pattern.compile(BARREL_PATTERN.getPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        int volume = Integer.parseInt(matcher.group(1));
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        validateMaxStringLength(storedMaterial, "Хранимый материал");
        validateMaxStringLength(materialOfManufacture, "Материал бочки");

        if (!validateMaxPossibleIntValue(volume, Integer.parseInt(maxValue))) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", Integer.parseInt(maxValue)));
        }

    }
}
