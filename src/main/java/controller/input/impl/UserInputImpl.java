package controller.input.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

import java.util.Optional;
import java.util.Scanner;

public class UserInputImpl implements UserInput {

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public MenuPoints getCommand() throws NotExistCommandException {
        Optional<MenuPoints> ifContains = MenuPoints.getIfContains(getInput());
        if (ifContains.isPresent()) {
            return ifContains.get();
        }
        throw  new NotExistCommandException("Нет команды");
    }

    @Override
    public String getDataFromUserInput() {
        return getInput();
    }
}
