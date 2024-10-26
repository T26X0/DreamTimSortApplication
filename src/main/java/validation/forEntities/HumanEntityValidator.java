package validation.forEntities;

import validation.exception.*;

public interface HumanEntityValidator {
    void validateEntityString(String human) throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException, PatternMismatchException, NegativeNumberException;
}
