package controller.user.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.input.impl.UserInputImpl;
import controller.user.UserController;
import controller.user.constants.MenuPoints;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController {

    private final UserInput userInput;

    private List<Exception> exceptions;

    public UserControllerImpl() {
        this.userInput = new UserInputImpl();
        this.exceptions = new ArrayList<>();
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
            case READING_DATA_FROM_FILE -> {}
            case CREATING_RAND_DATA -> {}
            case ENTERING_DATA_IN_TERMINAL -> {}
            case RESET_CACHE -> {}
            case SORTING_DATA_FROM_CACHE -> {}
            case RESET_FILE_FOR_DATA -> {}
            case EXIT -> closeApp();
        }
    }

    @Override
    public void closeApp() {

    }
}
