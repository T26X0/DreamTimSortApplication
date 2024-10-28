package model.service;

import java.util.List;

/**
 * Класс для чтения данных из репозитория
 В нашем случае репозиторий это .txt файл,
 Сервис расчитан на то, чтобы можно было был последствий заменить реализацию репозитория
 Изменить место хранения данных
 (Example: .txt -> DB)
 */
public interface DataService {

    String getData();

    void saveDataToLocalFile(String dataForSave, String fileName);

    void clearDataFromLocalDirectory();
}
