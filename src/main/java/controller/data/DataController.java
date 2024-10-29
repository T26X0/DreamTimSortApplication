package controller.data;

import model.constants.Entities;
import model.entity.sortable.Sortable;

import java.util.List;

/**
 * DataController занимается обработкой данных
 */
public interface DataController {

    <T extends Sortable> List<T> getListEntities(Entities entities);

    List<Sortable> readData() throws Exception;

    List<Sortable> generateData(int limit) throws Exception;

    List<Sortable> getDataFromCache();

    List<Sortable> sortData();

    void saveDataInCache(List<Sortable> listData);

    void saveCacheInLocalFile();

    void saveCacheInLocalFileByEntities();

    void clearCache();

    boolean cacheIsNotClear();

    void clearDataFromLocalDirectory();

    List<Sortable> convertStringToSortableList(String string) throws Exception;
}
