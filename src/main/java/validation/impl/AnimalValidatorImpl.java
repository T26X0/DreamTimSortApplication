package validation.impl;

import static validation.util.ValidationConstants.EntityConstants.HAS_NO_WOOL;
import static validation.util.ValidationConstants.EntityConstants.HAS_WOOL;
import static validation.util.ValidatorUtil.validateMaxStringLength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.util.ValidationConstants.RegexPatterns;

public class AnimalValidatorImpl implements Validator {

    @Override
    public void validate(String animal)
        throws ExceedingPermissibleLengthException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(RegexPatterns.ANIMAL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(animal);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну.");
        }

        String species = matcher.group(1);
        String eyeColor = matcher.group(2);
        String wool = matcher.group(3);

        validateMaxStringLength(species, "Вид животного");
        validateMaxStringLength(eyeColor, "Цвет глаз");

        if (!wool.equalsIgnoreCase(HAS_WOOL) && !wool.equalsIgnoreCase(HAS_NO_WOOL)) {
            throw new IncorrectDataTypeException(String.format(
                "Наличие шерсти должно быть '%s' или '%s'.", HAS_WOOL, HAS_NO_WOOL));
        }
    }
}
