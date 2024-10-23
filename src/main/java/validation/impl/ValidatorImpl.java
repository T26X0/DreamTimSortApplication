package validation.impl;

import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.IncorrectDataTypeException;
import validation.exception.PatternMismatchException;

public class ValidatorImpl implements Validator {

    @Override
    public void validationToPatternBasicData(String string) throws PatternMismatchException {

    }

    @Override
    public void validationAnimal(String userSample) throws ExceedingPermissibleLengthException, IncorrectDataTypeException {

    }

    @Override
    public void validationBarrel(String userSample) throws ExceedingPermissibleLengthException {

    }

    @Override
    public void validationHuman(String userSample) throws ExceedingPermissibleLengthException, IncorrectAgeException, IncorrectDataTypeException {

    }
}
