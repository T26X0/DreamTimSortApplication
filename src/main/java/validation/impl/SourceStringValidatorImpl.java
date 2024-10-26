package validation.impl;

import static validation.util.ValidationConstants.RegexPatterns.SOURCE_STRING_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.PatternMismatchException;

public class SourceStringValidatorImpl implements Validator {

    @Override
    public void validate(String source) throws PatternMismatchException {

        Pattern pattern = Pattern.compile(SOURCE_STRING_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);

        if(!matcher.matches()) {
            throw new PatternMismatchException("Исходная строка не соответствует паттерну.");
        }
    }
}
