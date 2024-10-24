package validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.util.Patterns;

public class BarrelValidatorImpl implements Validator {
    public void validate(String barrel) throws ExceedingPermissibleLengthException {
        Pattern pattern = Pattern.compile(Patterns.BARREL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(barrel);

        String volume = matcher.group(1);
        String storedMaterial = matcher.group(2);
        String materialOfManufacture = matcher.group(3);

        if (volume.length() > 30) {
            throw new ExceedingPermissibleLengthException("Первое значение превышает 30 символов.");
        }
        if (storedMaterial.length() > 30) {
            throw new ExceedingPermissibleLengthException("Второе значение превышает 30 символов.");
        }
        if (materialOfManufacture.length() > 30) {
            throw new ExceedingPermissibleLengthException("Третье значение превышает 30 символов.");
        }
    }
}
