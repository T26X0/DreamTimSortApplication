package validation.impl;

import static validation.util.ValidatorUtil.hairCheck;
import static validation.util.ValidatorUtil.maxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class AnimalValidatorImpl implements Validator {

    private static final int MAX_SPECIES_LENGTH = 30;
    private static final int MAX_EYE_LENGTH = 30;

    @Override
    public void validate(String animal)
        throws ExceedingPermissibleLengthException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(Patterns.ANIMAL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(animal);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String species = matcher.group(1);
        String eyeColor = matcher.group(2);
        String hair = matcher.group(3);


        if (maxStringLength(species, MAX_SPECIES_LENGTH)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Вид животного не должен превышать %d символов.", MAX_SPECIES_LENGTH));
        }

        if (maxStringLength(eyeColor, MAX_EYE_LENGTH)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Цвет глаз не должен превышать %d символов.", MAX_EYE_LENGTH));
        }

        if (hairCheck(hair)) {
            throw new IncorrectDataTypeException("Наличие шерсти должно быть 'yes' или 'no'.");
        }
    }
}
