package validation.forEntities;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;

public interface Validator {

    void validationEntityString(String stringEntity) throws Exception;
}
