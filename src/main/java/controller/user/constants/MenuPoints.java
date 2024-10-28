package controller.user.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum MenuPoints {

    READING_DATA_FROM_FILE("1", "1. Прочитать данные из файла"),
    CREATING_RAND_DATA("2", "2. Создать рандомные данные"),
    ENTERING_DATA_IN_TERMINAL("3", "3. Ввести данные из терминала"),
    SORTING_DATA_FROM_CACHE("4", "4. Сортировать сохраненные в кэше данные"),
    RESET_CACHE("5", "5. Сбрость кэш"),
    RESET_FILE_FOR_DATA("6", "6. Сбросить рабочую директорию"),
    SHOW_FULL_CACHE("7", "7. Отобразить весь кэш"),
    SHOW_ALL_LISTS_WITH_SORTED_DATA_BY_ENTITY("8", "8. Отобразить списки с сущностями"),
    EXIT("9", "9. Выйти из программы");

    private final String pointId;

    private final String pointName;

    MenuPoints(String pointId, String pointName) {
        this.pointId = pointId;
        this.pointName = pointName;
    }

    public static Optional<MenuPoints> getIfContains(String command) {
        return Arrays.stream(MenuPoints.values())
                .filter(p->p.getPointId().equals(command))
                .findFirst();
    }
}
