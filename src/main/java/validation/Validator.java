package validation;

import static model.repository.constants.EntityConstants.MAX_AGE;

/**
 * Валидатор проверяет корректность данных на соответствие шаблону данных
 * ----------------------------------------------------
 * Идея задать регулярное выражение и проверять по нему
 * <pre>
 * basicDataPattern String(String,String,String);
 *
 * animalPattern Animal("String" -> не больше 30 символов,
 *                      "String" -> не больше 30 символов,
 *                      "String" -> да или нет);
 * barrelPattern Barrel(int -> не больше 250 литров,
 *                      "String" -> не больше 50 символов,
 *                      "String" -> не больше 50 символов);
 * humanPattern Human("String" -> не больше 20 символов,
 *                     int -> не больше 100,
 *                     "String" -> не больше 30 символов);
 *                     </pre>
 */
public interface Validator {
    /**
     * Валидация на соответствие общему шаблону производится в первый круг вылидации
     * В нашем случае сразу после прочтения из файла или ввода пользователя
     */
    void validate(String entity) throws Exception;
}
