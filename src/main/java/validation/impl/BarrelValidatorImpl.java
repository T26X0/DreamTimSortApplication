package validation.impl;

import static model.repository.constants.ValidationConstants.EntityConstants.MAX_VOLUME;
import static model.repository.constants.ValidationConstants.RegexPatterns.BARREL_PATTERN;
import static validation.util.ValidatorUtil.validateMaxPossibleIntValue;
import static validation.util.ValidatorUtil.validateMaxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.PatternMismatchException;

public class BarrelValidatorImpl implements Validator {

    @Override
    public void validate(String barrel)
        throws ExceedingPermissibleLengthException, PatternMismatchException {

        Pattern pattern = Pattern.compile(BARREL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        int volume = Integer.parseInt(matcher.group(1));
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        validateMaxStringLength(storedMaterial, "Хранимый материал");
        validateMaxStringLength(materialOfManufacture, "Материал бочки");

        if (!validateMaxPossibleIntValue(volume, MAX_VOLUME)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", MAX_VOLUME));
        }

    }
}
