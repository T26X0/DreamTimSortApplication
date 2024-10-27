package validation.forEntities.impl;

import static model.repository.constants.EntityConstants.*;
import static model.repository.constants.EntityPatternsRegex.FIELD_PATTERN;
import static model.repository.constants.EntityPatternsRegex.HUMAN_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.forEntities.HumanEntityValidator;
import validation.exception.*;
import validation.forData.impl.IntegerValidatorImpl;
import validation.forData.impl.StringValidatorImpl;
import validation.forData.IntegerValidator;
import validation.forData.StringValidator;

public class HumanEntityValidatorImpl implements HumanEntityValidator {

    StringValidator stringValidator = new StringValidatorImpl();
    IntegerValidator integerValidator = new IntegerValidatorImpl();

    private final int maxAge = Integer.parseInt(MAX_AGE.getValue());

    private final String genderMale = GENDER_MALE.getValue();

    private final String genderFemale = GENDER_FEMALE.getValue();

    @Override
    public void validateEntityString(String animal) throws ExceedingPermissibleLengthException, IncorrectDataTypeException, NegativeNumberException {

        Pattern pattern = Pattern.compile(FIELD_PATTERN.getPattern());
        Matcher matcher = pattern.matcher(animal);
        List<String> list = new ArrayList<>();
        if (matcher.find()) {
            list = List.of(matcher.group(1).split("\\s*,\\s*"));
        }
        String gender = list.get(0);
        int age = Integer.parseInt(list.get(1));
        String name = list.get(2);

        stringValidator.validateMaxStringLength(name);
        integerValidator.validateMaxPossibleIntValue(age, maxAge);

        if (!gender.equalsIgnoreCase(genderMale) && !gender.equalsIgnoreCase(genderFemale)) {
            throw new IncorrectDataTypeException(String.format(
                    "Гендер можеть быть только '%s' или '%s'.", genderMale, genderFemale));
        }
    }
}

