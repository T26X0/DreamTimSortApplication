package view.display.config.constants;

import java.util.HashMap;
import java.util.Map;

public enum TextBlocks {

    /**
     * Provides TWO LINE
     */
    TITLE(DisplayConst.titleTextBlockCondition),

    /**
     * Provides TWO LINES after "TextBlocks.TITLE"
     */
    MENU_POINT(DisplayConst.menuPointTextBlockCondition),

    /**
     * Provides all the remaining lines
     */
    CONTENT(DisplayConst.contentTextBlockCondition),
    VOID(new HashMap<>());

    public final Map<Integer, Boolean> coordinates;

    TextBlocks(Map<Integer, Boolean> lines) {
        this.coordinates = lines;
    }
}

