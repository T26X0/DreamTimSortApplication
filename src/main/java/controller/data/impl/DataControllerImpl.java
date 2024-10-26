package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import controller.data.sort.TimSort;
import model.DataService;
import model.entity.sortable.Sortable;
import model.impl.DataServiceImpl;
import validation.Validator;
import validation.impl.DynamicEntityValidator;
import validation.impl.IsPositive;
import validation.impl.SourceStringValidatorImpl;

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
    public List<Sortable> readData() throws Exception {

        String data = dataService.getData();

        Validator validator = new SourceStringValidatorImpl();
        validator.validate(data);

        String[] processedData = StringProcessor.process(data);

        Validator dynamicEntityValidator = new DynamicEntityValidator();

        for (String dataToValidate : processedData) {
            dynamicEntityValidator.validate(dataToValidate);
        }

        DynamicClassCreation dynamicallyCreatedClasses = new DynamicClassCreation();

        return dynamicallyCreatedClasses.creatureClass(processedData);
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

        return List.copyOf(savedData);
    }

    @Override
    public List<Sortable> sortData() {

        TimSort.timSort(savedData, GeneralComparatorUtil.getComparatorForSortableEntity());

        return savedData;
    }

    @Override
    public void saveDataInCache(List<Sortable> listData) {

        savedData = List.copyOf(listData);
    }

    @Override
    public void clearCache() {

        savedData = null;
    }

    @Override
    public boolean cacheIsClear() {

        if (Objects.isNull(savedData)) {
            return true;
        }

        return savedData.isEmpty();
    }

    private void generateInstances(List<Sortable> instances, int count, Supplier<Sortable> generator) {
        for (int j = 0; j < count; j++) {
            instances.add(generator.get());
        }
    }
}
