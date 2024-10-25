package controller.user.constants;

//import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum MenuPoints {

    READING_DATA_FROM_FILE("1"),
    CREATING_RAND_DATA("2"),
    ENTERING_DATA_IN_TERMINAL("3"),
    SORTING_DATA_FROM_CACHE("4"),
    RESET_CACHE("5"),
    RESET_FILE_FOR_DATA("6"),
    EXIT("7");

//    @Getter
    private final String pointId;

    MenuPoints(String pointId) {
        this.pointId = pointId;
    }

    public String getPointId(){ return pointId; }

    public static Optional<MenuPoints> getIfContains(String command) {
        return Arrays.stream(MenuPoints.values())
                .filter(p->p.getPointId().equals(command))
                .findFirst();
    }
}
