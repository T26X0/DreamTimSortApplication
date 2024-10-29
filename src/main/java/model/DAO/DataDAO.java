package model.DAO;

/**
 * Репозиторий читает данные из файла
 */
public interface DataDAO {

    String getData();

    void saveDataToLocalFile(String dataForSave, String fileName);

    void clearDataFromLocalDirectory();
}
