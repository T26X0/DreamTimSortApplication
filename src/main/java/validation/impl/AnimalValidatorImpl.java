package validation.impl;

import static validation.util.ValidatorUtil.hairCheck;
import static validation.util.ValidatorUtil.maxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.util.Patterns;

public class AnimalValidatorImpl implements Validator {

    public void validate(String animal)
        throws ExceedingPermissibleLengthException, IncorrectDataTypeException {

        Pattern pattern = Pattern.compile(Patterns.ANIMAL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(animal);

        String species = matcher.group(1); // cat
        String eyeColor = matcher.group(2); // white
        String hair = matcher.group(3); // yes/no

        if (maxStringLength(species, 30)) {
            throw new ExceedingPermissibleLengthException("Первое значение превышает 30 символов.");
        }
        if (maxStringLength(eyeColor, 30)) {
            throw new ExceedingPermissibleLengthException("Второе значение превышает 30 символов.");
        }
        if (hairCheck(hair)) {
            throw new IncorrectDataTypeException("Третье значение должно быть 'yes' или 'no'.");
        }
    }
}
