package validation.forData.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.constants.EntityConstants;
import validation.exception.ExceedingPermissibleLengthException;
import validation.forData.StringValidator;
import validation.exception.PatternMismatchException;

import static model.constants.EntityPatternsRegex.SOURCE_STRING_PATTERN;

public class StringValidatorImpl implements StringValidator {

    @Override
    public void validateSourceString(String source) throws PatternMismatchException {
        Pattern pattern = Pattern.compile(SOURCE_STRING_PATTERN.getPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if(!matcher.matches()) {
            throw new PatternMismatchException("Исходная строка не соответствует паттерну.");
        }
    }

    @Override
    public void validateMaxStringLength(String string) throws ExceedingPermissibleLengthException {
        int maxStringLength = Integer.parseInt(EntityConstants.MAX_STRING_LENGTH.getValue());

        if(string.length() > maxStringLength) {
            throw new ExceedingPermissibleLengthException(String.format(
                    "%s не должен превышать %d символов.", string, maxStringLength));
        }
    }
}