package controller.user.constants;

import lombok.Getter;

import java.util.Optional;

public enum MenuPoints {

    SHOW_MAIN_MENU_OPTIONS(10),
    READING_DATA_FROM_FILE(20),
    CREATING_RAND_DATA(30),
    ENTERING_DATA(40),
    SORTING_DATA(50),
    EXIT(60);

    @Getter
    private int pointId;

    MenuPoints(int pointId) {
        this.pointId = pointId;
    }

    public Optional<MenuPoints> getIfContains(String command) {
        return null;
    }
}
