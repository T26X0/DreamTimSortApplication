package validation.forData;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;

public interface StringValidator {

    void validateSourceString(String source) throws PatternMismatchException;

    void validateMaxStringLength(String string) throws ExceedingPermissibleLengthException;
}
