package controller.user.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.input.impl.UserInputImpl;
import controller.user.UserController;
import controller.user.constants.MenuPoints;

public class UserControllerImpl implements UserController {

    private final UserInput userInput;

    public UserControllerImpl() {
        this.userInput = new UserInputImpl();
    }

    @Override
    public void startTrackingUserAction() {

        MenuPoints command = null;
        try {
            command = userInput.getCommand();
        } catch (NotExistCommandException e) {
            // TODO: t26x0 -> здесь должна быть "адекватная" обработка исключения
            throw new RuntimeException(e);
        }

        while (true) {
            executeCommand(command);
        }
    }

    @Override
    public void executeCommand(MenuPoints command) {
        switch (command) {
            case SHOW_MAIN_MENU_OPTIONS -> {}
            case READING_DATA_FROM_FILE -> {}
            case CREATING_RAND_DATA -> {}
            case ENTERING_DATA -> {}
            case SORTING_DATA -> {}
            case EXIT -> closeApp();
        }
    }

    @Override
    public void closeApp() {

    }
}
