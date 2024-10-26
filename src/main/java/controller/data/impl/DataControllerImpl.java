package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import model.entity.sortable.Sortable;
import model.service.DataService;
import model.service.impl.DataServiceImpl;
import validation.forData.IntegerValidator;
import validation.forData.impl.IntegerValidatorImpl;

import java.util.*;
import java.util.function.Supplier;

public class DataControllerImpl implements DataController {

    private List<Sortable> savedData = null;

    private final DataService dataService;

    private final IntegerValidator integerValidator = new IntegerValidatorImpl();

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

        integerValidator.isPositive(limit);
        DataGeneration dataGen = new DataGenerationImpl();
        return dataGen.getRandomClassList(limit);


        // TODO: Обсудить и корректно использовать методы генератора
//        List<Integer> parts = NumberGeneratorUtil.generateParts(limit);
//        List<Sortable> instances = new ArrayList<>();
//        generateInstances(instances, parts.get(0), dataGen::getRandomAnimal);
//        generateInstances(instances, parts.get(1), dataGen::getRandomBarrel);
//        generateInstances(instances, parts.get(2), dataGen::getRandomHuman);
//        return instances;

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

    @Override
    public boolean cacheIsClear() {
        return false;
    }

    private void generateInstances(List<Sortable> instances, int count, Supplier<Sortable> generator) {
        for (int j = 0; j < count; j++) {
            instances.add(generator.get());
        }
    }
}
