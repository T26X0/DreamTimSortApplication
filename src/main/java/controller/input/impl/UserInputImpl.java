package controller.input.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

public class UserInputImpl implements UserInput {

    // подсказка: Scanner

    private String getInput() {
        return null;
    }

    @Override
    public MenuPoints getCommand() throws NotExistCommandException {
        return null;
    }

    /**
     * Возвращает только ПРОВАЛИДИРОВАННЫЕ данные
     */
    @Override
    public String getDataFromUserInput() {
        return null;
    }
}
