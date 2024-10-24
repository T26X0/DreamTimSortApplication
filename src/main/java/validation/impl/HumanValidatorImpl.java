package validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import validation.Validator;
import validation.exception.ExceedingPermissibleLengthException;
import validation.exception.IncorrectAgeException;
import validation.exception.PatternMismatchException;
import validation.util.Patterns;

public class HumanValidatorImpl implements Validator {

    public void validate(String human)
        throws ExceedingPermissibleLengthException, IncorrectAgeException, PatternMismatchException {
        Pattern pattern = Pattern.compile(Patterns.HUMAN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(human);

        if (!matcher.matches()) {
            throw new PatternMismatchException("Строка не соответствует паттерну 'Human'");
        }

        String Gender = matcher.group(1);
        String Age = matcher.group(2);
        String Name = matcher.group(3);

        if (Gender.length() > 20) {
            throw new ExceedingPermissibleLengthException("Первое значение превышает 30 символов.");
        }
        if (Integer.parseInt(Age) < 0 || Integer.parseInt(Age) > 100) {
            throw new IncorrectAgeException("Возраст не может быть больше 100 символов.");
        }
        if (Name.length() > 30) {
            throw new ExceedingPermissibleLengthException("Третье значение превышает 30 символов.");
        }
    }
}

