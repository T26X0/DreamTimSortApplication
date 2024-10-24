package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import model.DataService;
import model.entity.sortable.Sortable;
import model.impl.DataServiceImpl;

import java.util.*;

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

        List<Integer> parts = NumberGeneratorUtil.generateParts(limit);
        List<Sortable> instances = new ArrayList<>();
        DataGeneration dataGen = new DataGenerationImpl();

        for (int i = 0; i < parts.size(); i++) {
            int count = parts.get(i);
            if (i == 0) {
                for (int j = 0; j < count; j++) {
                    instances.add(dataGen.getRandomAnimal());
                }
            } else if (i == 1) {
                for (int j = 0; j < count; j++) {
                    instances.add(dataGen.getRandomBarrel());
                }
            } else if (i == 2) {
                for (int j = 0; j < count; j++) {
                    instances.add(dataGen.getRandomHuman());
                }
            }
        }

        return instances;
    }

    @Override
    public List<Sortable> getDataFromCache() {

        return null;
    }

    @Override
    public List<Sortable> sortData() {
        // сортировка всех данных по типу
        Collections.sort(savedData);

        // сортировка всех данных между собой
        savedData.sort(GeneralComparatorUtil.getComparatorForSortableEntity());
        return savedData;
    }

    @Override
    public void saveDataInCache(List<Sortable> listData) {

    }

    @Override
    public void clearCache() {

    }
}
