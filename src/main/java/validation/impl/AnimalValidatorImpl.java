package validation.impl;

import static validation.util.ValidationConstant.HAS_HAIR;
import static validation.util.ValidationConstant.HAS_NO_HAIR;
import static validation.util.ValidationConstant.MAX_STRING_LENGTH;
import static validation.util.ValidatorUtil.hairCheck;
import static validation.util.ValidatorUtil.matchesMaxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class AnimalValidatorImpl implements Validator {

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


        if (matchesMaxStringLength(species)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Вид животного не должен превышать %d символов.", MAX_STRING_LENGTH));
        }

        if (matchesMaxStringLength(eyeColor)) {
            throw new ExceedingPermissibleLengthException(String.format(
                "Цвет глаз не должен превышать %d символов.", MAX_STRING_LENGTH));
        }

        if (hairCheck(hair)) {
            throw new IncorrectDataTypeException(String.format(
                "Наличие шерсти должно быть '%s' или '%s'.", HAS_HAIR, HAS_NO_HAIR));
        }
    }
}
