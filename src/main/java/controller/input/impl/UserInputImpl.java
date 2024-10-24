package controller.input.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.user.constants.MenuPoints;

import java.util.Scanner;

public class UserInputImpl implements UserInput {

    // подсказка: Scanner

    private String getInput() {
        System.out.print("Введите данные: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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
