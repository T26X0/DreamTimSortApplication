package controller.data.impl;

import controller.data.DataController;
import model.DataService;
import model.entity.sortable.Sortable;
import model.impl.DataServiceImpl;

import java.util.Collections;
import java.util.List;

public class DataControllerImpl implements DataController {

    private List<Sortable> savedData = null;

    private final DataService dataService;

    public DataControllerImpl() {
        this.dataService = new DataServiceImpl();
    }

    /**
     * Отдает в UserController только ПРОВАЛИДИРОВАННЫЕ данные!
     */
    @Override
    public List<Sortable> readData() {
        return null;
    }

    @Override
    public List<Sortable> generateData(int limit) {

        return null;
    }

    @Override
    public List<Sortable> getDataFromCache() {

        return null;
    }

    @Override
    public List<Sortable> sortData() {
        Collections.sort(savedData);
        return savedData;
    }

    @Override
    public void saveDataInCache(List<Sortable> listData) {
    }

    @Override
    public void clearCache() {

    }
}
