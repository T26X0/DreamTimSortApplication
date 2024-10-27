package controller.user.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum MenuPoints {

    READING_DATA_FROM_FILE("1", "1. Прочитать данные из файла"),
    CREATING_RAND_DATA("2", "2. Создать рандомные данные"),
    ENTERING_DATA_IN_TERMINAL("3", "3. Ввести данные из терминала"),
    SORTING_DATA_FROM_CACHE("4", "4. Сортировать сохраненные в кэше данные"),
    RESET_CACHE("5", "5. Сбрость кэш"),
    RESET_FILE_FOR_DATA("6", "6. Сбросить рабочую директорию"),
    EXIT("7", "7. Выйти из программы");

    @Getter
    private final String pointId;
    private final String pointName;

    MenuPoints(String pointId, String pointName) {
        this.pointId = pointId;
        this.pointName = pointName;
    }

    public String getPointId(){ return pointId; }

    public static Optional<MenuPoints> getIfContains(String command) {
        return Arrays.stream(MenuPoints.values())
                .filter(p->p.getPointId().equals(command))
                .findFirst();
    }
}
