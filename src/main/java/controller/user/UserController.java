package controller.user;

import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер получает команды от пользователя и обращается к API приложения по работе с данными
 */
public interface UserController {

    List<Exception> exceptionsLog = new ArrayList<>();

    /**
     * Запускает отслеживание действий пользователя
     */
    void startTrackingUserAction() throws NotExistCommandException;

    void executeCommand(MenuPoints command);

    static void addException(Exception exception) {
        exceptionsLog.add(exception);
    }

    void closeApp();
    void readDataFromFile();
    void createRandomData();
    void enterDataInTerminal();
    void resetCache();
    void sortDataFromCache();
    void resetFileForData();
    void showAllListsWithSortedDataByEntity();
    void showFullCache();
}
