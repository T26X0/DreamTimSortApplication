package controller.user.impl;

import controller.data.DataController;
import controller.data.impl.DataControllerImpl;
import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.input.impl.UserInputImpl;
import controller.user.UserController;
import controller.user.constants.MenuPoints;
import model.entity.sortable.Sortable;
import view.display.TerminalUserDisplay;
import view.display.config.constants.CenterMod;
import view.display.config.constants.TextBlocks;
import view.display.exception.TextBlockFilledException;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController {

    private final DataController dataController;
    private final UserInput userInput;
    private final TerminalUserDisplay userDisplay;

    public UserControllerImpl() throws TextBlockFilledException {
        this.dataController = new DataControllerImpl();
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


    /**
     * Запилить норм визуал
     */

    @Override
    public void closeApp() {
        // Завершение программы
        System.exit(0);
    }

    @Override
    public void readDataFromFile() {
        try {
            List<Sortable> unSortedData = dataController.readData();

            StringBuilder displayData = new StringBuilder();
            for (Sortable item : unSortedData) {
                displayData.append(item.toString());
            }
            userDisplay.addRequiredField("Вывод данных с файла", TextBlocks.CONTENT, 2, CenterMod.MID);
            userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(displayData.toString(), 80), TextBlocks.CONTENT, 3, CenterMod.MID);

        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(e.getMessage(), TextBlocks.CONTENT, 3, CenterMod.MID);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex); // Запилить норм обработку
            }
        }

        // Чтение данных из файла

    }

    @Override
    public void createRandomData() {
        try {
            System.out.println(dataController.generateData(5));
        } catch (Exception e) {
            throw new RuntimeException(e); // Запилить норм обработку
        }
    }

    @Override
    public void enterDataInTerminal() {
        String dataFromUserInput = userInput.getDataFromUserInput();
        try {
            List<Sortable> entitiesFromTerminal = dataController.convertStringToSortableList(dataFromUserInput);
            dataController.saveDataInCache(entitiesFromTerminal);
        } catch (Exception e) {
            throw new RuntimeException(e); // Запилить норм обработку
        }
        // Ввод данных
    }

    @Override
    public void resetCache() {
        dataController.clearCache();
        // Сброс кэша
    }

    @Override
    public void sortDataFromCache() {
        dataController.sortData();
        // Сортировка данных в кэше
    }

    @Override
    public void resetFileForData() {
        dataController.clearDataFromLocalDirectory();
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

