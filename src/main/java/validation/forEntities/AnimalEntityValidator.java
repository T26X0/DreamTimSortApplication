package validation.forEntities;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;

public interface AnimalEntityValidator {
    void validateEntityString(String animal) throws ExceedingPermissibleLengthException, IncorrectDataTypeException, PatternMismatchException;
}

