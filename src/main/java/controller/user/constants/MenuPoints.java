package controller.user.constants;

import lombok.Getter;

import java.util.Optional;

public enum MenuPoints {

    SHOW_MAIN_MENU_OPTIONS(1),
    READING_DATA_FROM_FILE(2),
    CREATING_RAND_DATA(3),
    ENTERING_DATA(4),
    SORTING_DATA(5),
    EXIT(6);

    @Getter
    private int pointId;

    MenuPoints(int pointId) {
        this.pointId = pointId;
    }

    public Optional<MenuPoints> getIfContains(String command) {
        return null;
    }
}
