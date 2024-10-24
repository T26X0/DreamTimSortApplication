package validation.impl;

import static validation.util.ValidationConstant.MAX_STRING_LENGTH;
import static validation.util.ValidationConstant.MAX_VOLUME;
import static validation.util.ValidatorUtil.matchesMaxStringLength;
import static validation.util.ValidatorUtil.compareValueWithMaxPossible;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class BarrelValidatorImpl implements Validator {

    @Override
    public void validate(String barrel)
        throws ExceedingPermissibleLengthException, PatternMismatchException {

        Pattern pattern = Pattern.compile(Patterns.BARREL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        int volume = Integer.parseInt(matcher.group(1));
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        if (compareValueWithMaxPossible(volume, MAX_VOLUME)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", MAX_VOLUME));
        }

        if (matchesMaxStringLength(storedMaterial)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Название хранимого материала должно быть не больше %d символов.",
                MAX_STRING_LENGTH));
        }

        if (matchesMaxStringLength(materialOfManufacture)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Название материала бочки не может быть длиннее %d символов.",
                MAX_STRING_LENGTH));
        }
    }
}
