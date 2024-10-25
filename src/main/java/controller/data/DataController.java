package controller.data;

import model.entity.sortable.Sortable;

import java.util.List;

/**
 * DataController занимается обработкой данных
 */
public interface DataController {

    List<Sortable> readData();

    List<Sortable> generateData(int limit) throws Exception;

    List<Sortable> getDataFromCache();

    List<Sortable> sortData();

    void saveDataInCache(List<Sortable> listData);

    void clearCache();
}
