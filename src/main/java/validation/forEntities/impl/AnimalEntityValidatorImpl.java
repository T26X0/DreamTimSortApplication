package validation.forEntities.impl;

import static model.repository.constants.EntityConstants.HAS_NO_WOOL;
import static model.repository.constants.EntityConstants.HAS_WOOL;
import static model.repository.constants.EntityPatternsRegex.ANIMAL_PATTERN;
import static model.repository.constants.EntityPatternsRegex.FIELD_PATTERN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.forEntities.AnimalEntityValidator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;
import validation.forData.impl.StringValidatorImpl;
import validation.forData.StringValidator;

public class AnimalEntityValidatorImpl implements AnimalEntityValidator {

    StringValidator stringValidator = new StringValidatorImpl();
    private final String hasWoolValue = HAS_WOOL.getValue();
    private final String hasNoWoolValue = HAS_NO_WOOL.getValue();


    @Override
    public void validateEntityString(String animal) throws ExceedingPermissibleLengthException, IncorrectDataTypeException, PatternMismatchException {

        Pattern pattern = Pattern.compile(FIELD_PATTERN.getPattern());
        Matcher matcher = pattern.matcher(animal);
        List<String> list = new ArrayList<>();
        if (matcher.find()) {
            list = List.of(matcher.group(1).split("\\s*,\\s*"));
        }
        String species = list.get(0);
        String eyeColor = list.get(1);
        String wool = list.get(2);

        stringValidator.validateMaxStringLength(species);
        stringValidator.validateMaxStringLength(eyeColor);

        if (!wool.equalsIgnoreCase(hasWoolValue) && !wool.equalsIgnoreCase(hasNoWoolValue)) {
            throw new IncorrectDataTypeException(String.format(
                "Наличие шерсти должно быть '%s' или '%s'.", hasNoWoolValue, hasNoWoolValue));
        }
    }
}
