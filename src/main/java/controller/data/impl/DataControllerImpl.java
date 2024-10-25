package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import model.DataService;
import model.entity.sortable.Sortable;
import model.impl.DataServiceImpl;
import validation.impl.IsPositive;

import java.util.*;
import java.util.function.Supplier;

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
    public List<Sortable> generateData(int limit) throws Exception {

        IsPositive.validate(limit);

        List<Integer> parts = NumberGeneratorUtil.generateParts(limit);
        List<Sortable> instances = new ArrayList<>();
        DataGeneration dataGen = new DataGenerationImpl();

        generateInstances(instances, parts.get(0), dataGen::getRandomAnimal);
        generateInstances(instances, parts.get(1), dataGen::getRandomBarrel);
        generateInstances(instances, parts.get(2), dataGen::getRandomHuman);

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

    private void generateInstances(List<Sortable> instances, int count, Supplier<Sortable> generator) {
        for (int j = 0; j < count; j++) {
            instances.add(generator.get());
        }
    }
}
