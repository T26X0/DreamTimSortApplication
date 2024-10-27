package view.display;

import view.display.config.DisplayController;
import view.display.config.constants.CenterMod;
import view.display.config.constants.DisplayConst;
import view.display.config.constants.TextBlocks;
import view.display.exception.TextBlockFilledException;

public class TerminalUserDisplay extends DisplayController {

    /**
     * <pre>Minimal values for app size:
     *      x > 50,
     *      y > 30.</pre>
     * <pre> :
     *      x > 60,
     *      y > 23.</pre>
     */
    public TerminalUserDisplay(int x, int y, String title, boolean debug) {
        DisplayConst.appInDebugUse = debug;

        sizeDisplayX = x;
        sizeDisplayY = y;
        initTextBlocks();

        appName = title;
        addAppName();
        initDisplayFrames();
    }

    public void show() {
        purifyTerminal();
        addRequiredElementsToDisplay();
        showAllDisplay();
    }

    public TerminalUserDisplay resetRequiredFields() {
        resetRequiresFieldsFromDisplay();
        return this;
    }

    public TerminalUserDisplay update() {
        updateDisplay();
        return this;
    }

    public void addRequiredField(String str, TextBlocks textBlock, CenterMod inCenter)
            throws TextBlockFilledException {
        addNewElementsInRequiredAppField(str, textBlock, inCenter);
    }

    public void addRequiredField(String str, TextBlocks textBlock, int textLineIndex, CenterMod inCenter)
            throws TextBlockFilledException {
        addNewElementsInRequiredAppField(str, textBlock, textLineIndex, inCenter);
    }

    public void add(String str, TextBlocks textBlock, CenterMod inCenter) {
        addNewElement(str, textBlock, inCenter);
    }

    public void add(String str, TextBlocks textBlock, int lineIndex, CenterMod inCenter) throws TextBlockFilledException {
        addNewElement(str, textBlock, lineIndex, inCenter);
    }

    public String formatLongStringByDisplayWidth(String str, int width) {
        if (str.length() < width - 4) {
            return str;
        }
        String substring = str.substring(0, width - 10);
        StringBuilder stringBuilder = new StringBuilder("  ");
        stringBuilder.append(substring);
        stringBuilder.append("...");
        return stringBuilder.toString();
    }
}