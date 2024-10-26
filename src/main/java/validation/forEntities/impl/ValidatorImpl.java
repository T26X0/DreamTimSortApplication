package validation.forEntities.impl;

import validation.exception.PatternMismatchException;
import validation.forEntities.AnimalEntityValidator;
import validation.forEntities.BarrelEntityValidator;
import validation.forEntities.HumanEntityValidator;
import validation.forEntities.Validator;

public class ValidatorImpl implements Validator {

    private BarrelEntityValidator barrelValidator = new BarrelEntityValidatorImpl();
    private AnimalEntityValidator animalValidator = new AnimalEntityValidatorImpl();
    private HumanEntityValidator humanValidator = new HumanEntityValidatorImpl();

    @Override
    public void validationEntityString(String inputString) throws Exception {
            if (inputString.toLowerCase().startsWith("human")) {
                humanValidator.validateEntityString(inputString);
            }
            else if (inputString.toLowerCase().startsWith("barrel")) {
                barrelValidator.validateEntityString(inputString);
            }
            else if (inputString.toLowerCase().startsWith("animal")) {
                animalValidator.validateEntityString(inputString);
            }
            else {
                throw new PatternMismatchException("Строка не соответствует ни одному из существующих паттернов.");
            }
    }
}
