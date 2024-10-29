package controller.user.impl;

import controller.data.DataController;
import controller.data.impl.DataControllerImpl;
import controller.input.UserInput;
import controller.input.exception.NotExistCommandException;
import controller.input.impl.UserInputImpl;
import controller.user.UserController;
import controller.user.constants.MenuPoints;
import model.constants.Entities;
import model.entity.sortable.Sortable;
import view.display.TerminalUserDisplay;
import view.display.config.constants.CenterMod;
import view.display.config.constants.TextBlocks;
import view.display.exception.TextBlockFilledException;

import java.util.List;

public class UserControllerImpl implements UserController {

    private final int widthDisplay = 80;
    private final int heightDisplay = 30;

    private final DataController dataController;
    private final UserInput userInput;
    private final TerminalUserDisplay userDisplay;

    public UserControllerImpl() throws TextBlockFilledException {
        this.dataController = new DataControllerImpl();
        this.userInput = new UserInputImpl();
        this.userDisplay = new TerminalUserDisplay(widthDisplay, heightDisplay, "DreamTimSortApplication", true);
        initializeMenuDisplay();
    }

    @Override
    public void startTrackingUserAction() {
        while (true) {
            userDisplay.show();
            MenuPoints command;
            try {
                command = userInput.getCommand();
            } catch (NotExistCommandException e) {
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
            case SORTING_DATA_FROM_CACHE -> sortDataFromCache();
            case SAVE_IN_FILE -> saveCacheInLocalFile();
            case SAVE_IN_FILE_BY_ENTITIES -> saveAllListsWithSortedDataByEntity();
//            case FIND_BY_ENTITY -> ;
//            case FIND_BY_FIELD -> ;
            case RESET_CACHE -> resetCache();
            case RESET_FILE_FOR_DATA -> resetFileForData();
            case SHOW_FULL_CACHE -> showFullCache();
            case SHOW_ALL_LISTS_WITH_SORTED_DATA_BY_ENTITY -> showAllListsWithSortedDataByEntity();
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
        try {
            List<Sortable> unSortedData = dataController.readData();

            StringBuilder displayData = new StringBuilder();
            for (Sortable item : unSortedData) {
                displayData.append(item.toString());
            }
            dataController.saveDataInCache(unSortedData);
            userDisplay.addRequiredField("Прочитанные с файла данные  " + userDisplay.formatLongStringByDisplayWidth(displayData.toString(), widthDisplay), TextBlocks.MENU_POINT, CenterMod.LEFT);

        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void createRandomData() {
        try {
            // TODO: отображение запроса на ввод лимита
            // TODO: проверку на инт
            int limit = Integer.parseInt(userInput.getDataFromUserInput());
            List<Sortable> sortables = dataController.generateData(limit);
            String sortablesDataForDisplay = sortables.toString();
            userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth("Сгенерированные данные: " + sortablesDataForDisplay, widthDisplay), TextBlocks.MENU_POINT, CenterMod.LEFT);
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void enterDataInTerminal() {
        String dataFromUserInput = userInput.getDataFromUserInput();
        try {
            List<Sortable> entitiesFromTerminal = dataController.convertStringToSortableList(dataFromUserInput);
            dataController.saveDataInCache(entitiesFromTerminal);
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
        }
        // Ввод данных
    }

    @Override
    public void saveCacheInLocalFile() {
        dataController.saveCacheInLocalFile();
    }

    @Override
    public void resetCache() {
        try {
            dataController.clearCache();
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
        }
        // Сброс кэша
    }

    @Override
    public void sortDataFromCache() {
        try {
            dataController.sortData();
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void resetFileForData() {
        try {
            dataController.clearDataFromLocalDirectory();
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
            // Сброс рабочей директории
        }
    }

    @Override
    public void showAllListsWithSortedDataByEntity() {
        System.out.println(dataController.getListEntities(Entities.ANIMAL));
        System.out.println(dataController.getListEntities(Entities.BARREL));
        System.out.println(dataController.getListEntities(Entities.HUMAN));
    }

    @Override
    public void saveAllListsWithSortedDataByEntity() {
        dataController.saveCacheInLocalFileByEntities();
    }

    @Override
    public void showFullCache() {
        try {
            List<Sortable> dataFromCache = dataController.getDataFromCache();
            String dataFromCacheString = dataFromCache.toString();
            System.out.println("dataFromCacheString:: " + dataFromCacheString);
            String stringForDisplay = userDisplay.formatLongStringByDisplayWidth(dataFromCacheString, widthDisplay);
            System.out.println("stringForDisplay:: " + stringForDisplay);
            userDisplay.addRequiredField(stringForDisplay, TextBlocks.MENU_POINT, CenterMod.LEFT);
        } catch (Exception e) {
            try {
                userDisplay.addRequiredField(userDisplay.formatLongStringByDisplayWidth(e.getMessage(), widthDisplay), TextBlocks.CONTENT, CenterMod.LEFT);
            } catch (TextBlockFilledException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    private void initializeMenuDisplay() throws TextBlockFilledException {
        String startLine = "                  ";
        for (MenuPoints point : MenuPoints.values()) {
            userDisplay.addRequiredField(startLine + point.getPointName(), TextBlocks.TITLE, CenterMod.LEFT);
        }
        userDisplay.addRequiredField("-----------------------------------------------------------------------------", TextBlocks.MENU_POINT, CenterMod.LEFT);
        userDisplay.addRequiredField("-----------------------------------------------------------------------------", TextBlocks.CONTENT, CenterMod.LEFT);
    }
}

