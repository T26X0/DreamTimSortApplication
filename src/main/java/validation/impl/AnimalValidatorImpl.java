package validation.impl;

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

        if (species.length() > 30) {
            throw new ExceedingPermissibleLengthException("Первое значение превышает 30 символов.");
        }
        if (eyeColor.length() > 30) {
            throw new ExceedingPermissibleLengthException("Второе значение превышает 30 символов.");
        }
        if (!hair.equalsIgnoreCase("yes") && !hair.equalsIgnoreCase("no")) {
            throw new IncorrectDataTypeException("Третье значение должно быть 'yes' или 'no'.");
        }
    }
}
