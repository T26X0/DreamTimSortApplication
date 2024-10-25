package validation.impl;

import validation.Validator;
import validation.exception.PatternMismatchException;

public class DynamicValidator implements Validator{

    private final Validator humanValidator;
    private final Validator barrelValidator;
    private final Validator animalValidator;

    public DynamicValidator() {
        this.humanValidator = new HumanValidatorImpl();
        this.barrelValidator = new BarrelValidatorImpl();
        this.animalValidator = new AnimalValidatorImpl();
    }
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
}
