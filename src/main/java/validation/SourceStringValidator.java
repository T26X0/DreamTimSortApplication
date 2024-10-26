package validation;

import validation.exception.PatternMismatchException;

public interface SourceStringValidator {

    void validateSourceString(String source) throws PatternMismatchException;
}
