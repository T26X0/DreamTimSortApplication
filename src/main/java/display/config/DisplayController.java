package display.config;

import display.config.constants.CenterMod;
import display.config.constants.DisplayConst;
import display.config.constants.TextBlocks;
import display.exception.TextBlockFilledException;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayController extends DisplayConst {

    private static int lineCounterForDebug = 0;
    protected int[][] locationTopLineFrame;
    protected int[][] locationDownLineFrame;
    protected int[][] location_left_lineFrame;
    protected int[][] location_right_lineFrame;

    protected Map<String, String> allDisplayValue = resetMap();
    protected Map<String, String> requiredFields = resetMap();

    public void showLogo() {
        System.out.println(logo_image);

    }

    protected void initDisplayFrames() {
        locationTopLineFrame = new int[sizeDisplayX][2];
        locationDownLineFrame = new int[sizeDisplayX][2];

        location_left_lineFrame = new int[sizeDisplayY - 1][2];
        location_right_lineFrame = new int[sizeDisplayY - 1][2];
        nextCommandForUserLocation = new int[]{1, sizeDisplayY - 1};
        allDisplayValue = resetMap();

        createHorizontalLine();
        createVerticalLine();

        updateDisplay();
    }

    protected void updateDisplay() {

        addRequiredElementsToDisplay();
        addAppName();
        setFrameInDisplay();

        fillEmptiness();
        setNextCommandForUser();
    }

    protected void resetRequiresFieldsFromDisplay() {
        Map<String, String> mapWithSpace = changeAllValueToSpace(requiredFields);
        addMapToMap(mapWithSpace, allDisplayValue);
        requiredFields = resetMap();
    }

    private Map<String, String> changeAllValueToSpace(Map<String, String> mapForUpdating) {
        List<String> keys = new ArrayList<>(mapForUpdating.keySet());

        for (String key : keys) {
            mapForUpdating.put(key, " ");
        }

        return mapForUpdating;
    }

    public void resetAllDisplay() {
        allDisplayValue = resetMap();
        resetTextBlockBusy();
    }

    private void setFrameInDisplay() {

        for (int[] coordinates_X_Y : locationTopLineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = transformToStringCoordinates(x, y);
            allDisplayValue.put(coordinates, symbol_frame_x);
        }

        for (int[] coordinates_X_Y : locationDownLineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = transformToStringCoordinates(x, y);
            allDisplayValue.put(coordinates, symbol_frame_x);
        }

        for (int[] coordinates_X_Y : location_left_lineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = transformToStringCoordinates(x, y);
            allDisplayValue.put(coordinates, symbol_frame_y);
        }

        for (int[] coordinates_X_Y : location_right_lineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = transformToStringCoordinates(x, y);
            allDisplayValue.put(coordinates, symbol_frame_y);
        }
    }

    protected void addRequiredElementsToDisplay() {
        addMapToMap(requiredFields, allDisplayValue);
    }

    protected void addAppName() {
        int x = get_X_for_centering(appName);
        int y = 1;

        Map<String, String> stringWithCoordinates;

        stringWithCoordinates = prepareLineToInsertInMap(x, y, appName);
        addMapToMap(stringWithCoordinates, allDisplayValue);
    }

    private void fillEmptiness() {
        for (int x = 0; x < sizeDisplayX; x++) {
            for (int y = 0; y < sizeDisplayY; y++) {
                String coordinates = transformToStringCoordinates(x, y);
                allDisplayValue.putIfAbsent(coordinates, " ");
            }
        }
    }

    protected void setNextCommandForUser() {
        if (nextCommandForUser == null) {
            nextCommandForUser = " ";
        }

        Map<String, String> title_map = prepareLineToInsertInMap(
                nextCommandForUserLocation[X_POINT],
                nextCommandForUserLocation[Y_POINT],
                nextCommandForUser);
        addMapToMap(title_map, allDisplayValue);
    }

    protected void addNewElement
            (String str, TextBlocks textBlock, int lineIndex, CenterMod inCenter) {
        Map<String, String> mapWithCoordinates;

        mapWithCoordinates = getMapForInsert(str, textBlock, lineIndex, inCenter);
        addMapToMap(mapWithCoordinates, allDisplayValue);
    }

    protected void addNewElement
            (String str, TextBlocks textBlock, CenterMod inCenter) {
        addNewElement(str, textBlock, DEFAULT_LINE_INDEX, inCenter);
    }

    protected void addNewElementsInRequiredAppField
            (String str, TextBlocks textBlock, int lineIndex, CenterMod inCenter) {

        Map<String, String> mapWithCoordinates = getMapForInsert(str, textBlock, lineIndex, inCenter);
        addMapToMap(mapWithCoordinates, requiredFields);
    }

    protected void addNewElementsInRequiredAppField
            (String str, TextBlocks textBlock, CenterMod inCenter) {
        addNewElementsInRequiredAppField(str, textBlock, DEFAULT_LINE_INDEX, inCenter);
    }

    protected Map<String, String> getMapForInsert
            (String str, TextBlocks textBlock, int lineIndex, CenterMod inCenter) {
        int[] coordinates;
        try {
            coordinates = getCoordinateForTextBlock(str, textBlock, inCenter);
        } catch (TextBlockFilledException e) {
            throw new RuntimeException(e);
        }

        if (lineIndex != DEFAULT_LINE_INDEX) {
            switch (textBlock) {
                case TITLE -> coordinates[Y_POINT] = positionTitleTextBlockHeight[0] + lineIndex;
                case MENU_POINT -> coordinates[Y_POINT] = menuPointTextBlockHeight[0] + lineIndex;
                case CONTENT -> coordinates[Y_POINT] = positionContentTextBlockHeight[0] + lineIndex;
                case VOID -> coordinates[Y_POINT] = lineIndex;
            }
        }

        Map<String, String> mapWithCoordinates;
        mapWithCoordinates = prepareLineToInsertInMap(
                coordinates[X_POINT],
                coordinates[Y_POINT],
                str);

        takePositionInTextBlock(textBlock, coordinates[Y_POINT]);
        return mapWithCoordinates;

    }

    private void takePositionInTextBlock(TextBlocks textBlock, int lineCoordinates) {

        switch (textBlock) {
            case TITLE -> titleTextBlockCondition.put(lineCoordinates, true);
            case MENU_POINT -> menuPointTextBlockCondition.put(lineCoordinates, true);
            case CONTENT -> contentTextBlockCondition.put(lineCoordinates, true);
        }
    }

    protected int[] getCoordinateForTextBlock(String str, TextBlocks userTextBlock, CenterMod centerMod) throws TextBlockFilledException {
        int x = 2;
        int y = NOT_CHANGED;
        List<Integer> keysFromUserTextBlock = null;
        Map<Integer, Boolean> currentMap = null;

        switch (centerMod) {
            case LEFT -> x = 2;
            case MID -> x = get_X_for_centering(str);
            case RIGHT -> x = sizeDisplayX - str.length() - 2;
        }

        switch (userTextBlock) {
            case TITLE -> {
                keysFromUserTextBlock = new ArrayList<>(titleTextBlockCondition.keySet());
                currentMap = titleTextBlockCondition;
            }
            case MENU_POINT -> {
                keysFromUserTextBlock = new ArrayList<>(menuPointTextBlockCondition.keySet());
                currentMap = menuPointTextBlockCondition;
            }
            case CONTENT -> {
                keysFromUserTextBlock = new ArrayList<>(contentTextBlockCondition.keySet());
                currentMap = contentTextBlockCondition;
            }
        }

        if (keysFromUserTextBlock == null) throw new IllegalArgumentException("keysFromUserTextBlock is null");
        Collections.sort(keysFromUserTextBlock);

        for (Integer key : keysFromUserTextBlock) {

            Boolean lineIsBusy = currentMap.get(key);
            if (!lineIsBusy) {
                y = key;
                break;
            }
        }
        if (y == NOT_CHANGED) {
            throw new TextBlockFilledException("There is not space in the text block");
        }

        return new int[]{x, y};
    }

    protected Map<String, String> prepareLineToInsertInMap(int x, int y, String str) {
        String coordinatesForKey;
        String letterForValue;
        Map<String, String> resultMap = new HashMap<>();

        char[] numbersOfChars = str.toCharArray();

        for (int i = 0; i < numbersOfChars.length; i++) {
            coordinatesForKey = transformToStringCoordinates(x + i, y);
            letterForValue = String.valueOf(numbersOfChars[i]);
            resultMap.put(coordinatesForKey, letterForValue);
        }
        return resultMap;
    }

    protected void addMapToMap(Map<String, String> give, Map<String, String> receive) {
        List<String> keys = new ArrayList<>(give.keySet());
        for (String key : keys) {
            String value = give.get(key);
            receive.put(key, value);
        }
    }

    protected void purifyTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void showAllDisplay() {

        for (int y = 0; y < sizeDisplayY; y++) {
            for (int x = 0; x < sizeDisplayX; x++) {
                String coordinates = transformToStringCoordinates(x, y);
                System.out.print(allDisplayValue.get(coordinates));
            }
            if (appInDebugUse) {
                printTextBlockName(y);
            }
            System.out.println();
        }
        lineCounterForDebug = 0;
    }

    private void printTextBlockName(int coordinateY) {

        TextBlocks textBlock = getTextBlockByCoordinate(coordinateY);
        String blockName = textBlock.name();

        if (blockName.equals("EMPTY")) blockName = "-";
        if (coordinateY == sizeDisplayY - 1) blockName = "next user command";
        if (coordinateY == locationTopLineFrame[0][Y_POINT]) blockName = "top line";
        if (coordinateY == 1) blockName = "app name";
        if (coordinateY == locationDownLineFrame[0][Y_POINT]) blockName = "down line";
        System.out.print(" -> " + +lineCounterForDebug + " : " + blockName);
        lineCounterForDebug++;
    }

    private void createHorizontalLine() {
        for (int queue = 0; queue < sizeDisplayX; queue++) {
            for (int cursor = X_POINT; cursor <= Y_POINT; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        locationTopLineFrame[queue][X_POINT] = queue;
                        locationDownLineFrame[queue][X_POINT] = queue;
                        break;
                    case Y_POINT:
                        locationTopLineFrame[queue][Y_POINT] = 0;
                        locationDownLineFrame[queue][Y_POINT] = headlineLocationY;
                        break;
                }
            }
        }
    }

    private void createVerticalLine() {

        for (int queue = 0; queue < sizeDisplayY - 1; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        location_left_lineFrame[queue][X_POINT] = 0;
                        location_right_lineFrame[queue][X_POINT] = sizeDisplayX - 1;
                        break;
                    case Y_POINT:
                        location_left_lineFrame[queue][Y_POINT] = queue + 1;
                        location_right_lineFrame[queue][Y_POINT] = queue + 1;
                        break;
                }
            }
        }
    }

    private static String transformToStringCoordinates(int x, int y) {
        return x + ", " + y;
    }

    private static String transformToStringCoordinates(String x, String y) {
        return x + ", " + y;
    }

    private static int[] transformCoordinatesToIntArray(String coordinates) {
        int[] result = new int[2];

        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(coordinates);

        for (int i = 0; matcher.find(); i++) {
            result[i] = Integer.parseInt(matcher.group());
        }

        return result;
    }

    protected Map<String, String> resetMap() {
        return new HashMap<>();
    }
}

