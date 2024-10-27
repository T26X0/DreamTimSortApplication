package validation.forData.impl;

import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.NegativeNumberException;
import validation.exception.PatternMismatchException;
import validation.forData.DataValidator;
import validation.forData.IntegerValidator;
import validation.forData.StringValidator;

public class DataValidatorImpl implements DataValidator {

    private final IntegerValidator integerValidator = new IntegerValidatorImpl();
    private final StringValidator stringValidator = new StringValidatorImpl();

    @Override
    public void isPositive(int number) throws NegativeNumberException {
        integerValidator.isPositive(number);
    }

    @Override
    public void validateMaxPossibleIntValue(int value, int maxPossible) throws ExceedingPermissibleLengthException, NegativeNumberException {
        integerValidator.validateMaxPossibleIntValue(value, maxPossible);
    }

    @Override
    public void validateSourceString(String source) throws PatternMismatchException {
        stringValidator.validateSourceString(source);
    }

    @Override
    public void validateMaxStringLength(String string) throws ExceedingPermissibleLengthException {
        stringValidator.validateMaxStringLength(string);
    }
}
