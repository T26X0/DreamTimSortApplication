package validation.impl;

import static model.repository.constants.ValidationConstants.RegexPatterns.SOURCE_STRING_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.SourceStringValidator;
import validation.Validator;
import validation.exception.PatternMismatchException;

public class DynamicEntityValidator implements Validator, SourceStringValidator {

    private final Validator humanValidator;
    private final Validator barrelValidator;
    private final Validator animalValidator;

    public DynamicEntityValidator() {
        this.humanValidator = new HumanValidatorImpl();
        this.barrelValidator = new BarrelValidatorImpl();
        this.animalValidator = new AnimalValidatorImpl();
    }

    @Override
    public void validate(String inputString) throws Exception {

        if (inputString.toLowerCase().startsWith("human")) {
            humanValidator.validate(inputString);
        }
        else if (inputString.toLowerCase().startsWith("barrel")) {
            barrelValidator.validate(inputString);
        }
        else if (inputString.toLowerCase().startsWith("animal")) {
            animalValidator.validate(inputString);
        }
        else {
            throw new PatternMismatchException("Строка не соответствует ни одному из существующих паттернов.");
        }
    }

    @Override
    public void validateSourceString(String source) throws PatternMismatchException {
        Pattern pattern = Pattern.compile(SOURCE_STRING_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if(!matcher.matches()) {
            throw new PatternMismatchException("Исходная строка не соответствует паттерну.");
        }
    }
}
