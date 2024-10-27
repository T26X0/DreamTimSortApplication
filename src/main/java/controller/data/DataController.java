package controller.data;

import model.entity.sortable.Sortable;

import java.util.List;

/**
 * DataController занимается обработкой данных
 */
public interface DataController {

    List<Sortable> readData() throws Exception;

    List<Sortable> generateData(int limit) throws Exception;

    List<Sortable> getDataFromCache();

    List<Sortable> sortData();

    void saveDataInCache(List<Sortable> listData);

    void clearCache();

    boolean cacheIsClear();

    void clearDataFromLocalDirectory();

    List<Sortable> convertStringToSortableList(String string) throws Exception;
}
