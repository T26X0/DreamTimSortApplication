package validation.forData.impl;

import validation.forEntities.AnimalEntityValidator;
import validation.forEntities.BarrelEntityValidator;
import validation.forEntities.HumanEntityValidator;
import validation.forEntities.impl.AnimalEntityValidatorImpl;
import validation.forEntities.impl.BarrelEntityValidatorImpl;
import validation.forEntities.impl.HumanEntityValidatorImpl;
import validation.exception.*;
import validation.forData.EntityValidator;

public class EntityValidatorImpl implements EntityValidator {

    private final BarrelEntityValidator barrelValidator = new BarrelEntityValidatorImpl();
    private final AnimalEntityValidator animalValidator = new AnimalEntityValidatorImpl();
    private final HumanEntityValidator humanValidator = new HumanEntityValidatorImpl();

    @Override
    public void validateEntityString(String entity) throws Exception {

        if (entity.toLowerCase().startsWith("human")) {
            humanValidator.validateEntityString(entity);
        }
        else if (entity.toLowerCase().startsWith("barrel")) {
            barrelValidator.validateEntityString(entity);
        }
        else if (entity.toLowerCase().startsWith("animal")) {
            animalValidator.validateEntityString(entity);
        }
        else {
            throw new PatternMismatchException("Строка не соответствует ни одному из существующих паттернов.");
        }
    }
}
