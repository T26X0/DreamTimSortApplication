package controller.input;

import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

/**
 * Класс для получения вводимых пользователем данных
 */
public interface UserInput {

    /**
     * Метод для получения от пользователя ввода и каст его к соответствующему пункту меню
     * Если такого пункта меню не найдено, то выбрасывается ошибка NotExistCommandException
     * @return
     */
    MenuPoints getCommand() throws NotExistCommandException;

    String getDataFromUserInput();
}
