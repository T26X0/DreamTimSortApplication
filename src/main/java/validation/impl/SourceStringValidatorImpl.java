package validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class SourceStringValidatorImpl implements Validator {

    @Override
    public void validate(String source) throws PatternMismatchException {

        Pattern pattern = Pattern.compile(Patterns.SOURCE_STRING_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if(!matcher.matches()){
            throw new PatternMismatchException("Исходная строка не соответствует паттерну.");
        }
    }
}
