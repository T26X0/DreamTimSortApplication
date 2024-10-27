package controller.user.impl;

import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.input.impl.UserInputImpl;
import controller.user.UserController;
import controller.user.constants.MenuPoints;
import view.display.TerminalUserDisplay;
import view.display.config.constants.CenterMod;
import view.display.config.constants.TextBlocks;
import view.display.exception.TextBlockFilledException;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController {

    private final UserInput userInput;
    private final TerminalUserDisplay userDisplay;

    public UserControllerImpl() throws TextBlockFilledException {
        this.userInput = new UserInputImpl();
        this.userDisplay = new TerminalUserDisplay(80, 30, "DreamTimSortApplication", true);
        initializeMenuDisplay();
    }

    @Override
    public void startTrackingUserAction() {
        while (true) {
            userDisplay.show();
            MenuPoints command;
            try {
                command = userInput.getCommand();
                System.out.println("Command: " + command);
            } catch (NotExistCommandException e) {
                System.out.println("Ошибка: " + e.getMessage());
                continue;
            }

            executeCommand(command);
        }
    }

    @Override
    public void executeCommand(MenuPoints command) {
        switch (command) {
            case READING_DATA_FROM_FILE -> readDataFromFile();
            case CREATING_RAND_DATA -> createRandomData();
            case ENTERING_DATA_IN_TERMINAL -> enterDataInTerminal();
            case RESET_CACHE -> resetCache();
            case SORTING_DATA_FROM_CACHE -> sortDataFromCache();
            case RESET_FILE_FOR_DATA -> resetFileForData();
            case EXIT -> closeApp();
        }
    }

    @Override
    public void closeApp() {
        // Завершение программы
        System.exit(0);
    }

    @Override
    public void readDataFromFile() {
        // Чтение данных из файла
    }

    @Override
    public void createRandomData() {
        // Создание случайных данных
    }

    @Override
    public void enterDataInTerminal() {
        // Ввод данных
    }

    @Override
    public void resetCache() {
        // Сброс кэша
    }

    @Override
    public void sortDataFromCache() {
        // Сортировка данных в кэше
    }

    @Override
    public void resetFileForData() {
        // Сброс рабочей директории
    }


    private void initializeMenuDisplay() throws TextBlockFilledException {
        String startLine = "                  ";
        for (MenuPoints point : MenuPoints.values()) {
            userDisplay.addRequiredField(startLine + point.getPointName(), TextBlocks.MENU_POINT, CenterMod.LEFT);
        }
        userDisplay.addRequiredField("-----------------------------------------------------------------------------", TextBlocks.CONTENT, CenterMod.LEFT);
    }
}

