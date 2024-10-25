package controller.user;

import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

/**
 * Контроллер получает команды от пользователя и обращается к API приложения по работе с данными
 */
public interface UserController {

    /**
     * Запускает отслеживание действий пользователя
     */
    void startTrackingUserAction() throws NotExistCommandException;

    void executeCommand(MenuPoints command);

    void closeApp();
    void readDataFromFile();
    void createRandomData();
    void enterDataInTerminal();
    void resetCache();
    void sortDataFromCache();
    void resetFileForData();
}


