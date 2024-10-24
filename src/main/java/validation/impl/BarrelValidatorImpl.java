package validation.impl;

import static validation.util.ValidatorUtil.maxStringLength;
import static validation.util.ValidatorUtil.maxValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.util.Patterns;

public class BarrelValidatorImpl implements Validator {

   private static final int MAX_BARREL_VOLUME = 250;
   private static final int MAX_STORED_MATERIAL_LENGTH = 50;
   private static final int MAX_BARREL_MATERIAL_LENGTH = 50;

    @Override
    public void validate(String barrel) throws ExceedingPermissibleLengthException {

        Pattern pattern = Pattern.compile(Patterns.BARREL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        int volume = Integer.parseInt(matcher.group(1));
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        if (maxValue(volume, MAX_BARREL_VOLUME)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Ёмкость бочки не может быть больше %d.", MAX_BARREL_VOLUME));
        }

        if (maxStringLength(storedMaterial, MAX_STORED_MATERIAL_LENGTH)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Название хранимого материала должно быть не больше %d символов.",
                MAX_BARREL_MATERIAL_LENGTH));
        }

        if (maxStringLength(materialOfManufacture, MAX_BARREL_MATERIAL_LENGTH)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Название материала бочки не может быть длиннее %d символов.",
                MAX_BARREL_MATERIAL_LENGTH));
        }
    }
}
