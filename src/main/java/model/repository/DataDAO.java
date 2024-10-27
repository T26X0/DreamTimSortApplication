package model.repository;

import java.util.List;

/**
 * Репозиторий читает данные из файла
 */
public interface DataDAO {

    String getData();

    void saveDataToLocalFile(String dataForSave, String fileName);
}
