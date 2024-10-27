package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.NumberGeneratorUtil;
import controller.data.generation.impl.DataGenerationImpl;
import controller.data.search.binary.exception.EmptyCacheException;
import controller.data.sort.TimSort;
import java.util.stream.Collectors;
import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;
import model.service.DataService;
import model.service.impl.DataServiceImpl;
import validation.forData.DataValidator;
import validation.forData.IntegerValidator;
import validation.forData.impl.DataValidatorImpl;
import validation.forData.impl.IntegerValidatorImpl;
import validation.forEntities.EntityValidator;
import validation.forEntities.impl.EntityValidatorImpl;

import java.util.*;
import java.util.function.Supplier;

public class DataControllerImpl implements DataController {

    private List<Sortable> savedData = null;
    List<Animal> savedAnimals = new ArrayList<>();
    List<Barrel> savedBarrels = new ArrayList<>();
    List<Human> savedHumans = new ArrayList<>();

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

        DataValidator dataValidator = new DataValidatorImpl();
        dataValidator.validateSourceString(data);

        String[] processedData = StringProcessor.process(data);

        EntityValidator entityValidator = new EntityValidatorImpl();

        for (String dataToValidate : processedData) {
            entityValidator.validationEntityString(dataToValidate);
        }

        DynamicClassCreation dynamicallyCreatedClasses = new DynamicClassCreation();

        return dynamicallyCreatedClasses.creatureClass(processedData);
    }

    @Override
    public List<Sortable> generateData(int limit) throws Exception {

        IntegerValidator integerValidator = new IntegerValidatorImpl();
        integerValidator.isPositive(limit);

        List<Integer> parts = NumberGeneratorUtil.generateParts(limit);
        List<Sortable> instances = new ArrayList<>();
        DataGeneration dataGen = new DataGenerationImpl();

//        generateInstances(instances, parts.get(0), dataGen::getRandomAnimal);
//        generateInstances(instances, parts.get(1), dataGen::getRandomBarrel);
//        generateInstances(instances, parts.get(2), dataGen::getRandomHuman);

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

    void saveDataToLocalFile() throws EmptyCacheException {

        separateCacheToUniqueLists();

        String animalString = savedAnimals.stream()
            .map(Animal::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(animalString, "sortedAnimals");

        String barrelString = savedBarrels.stream()
            .map(Barrel::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(barrelString, "sortedBarrels");

        String humanString = savedHumans.stream()
            .map(Human::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(humanString, "sortedHumans");
    }

    void separateCacheToUniqueLists() throws EmptyCacheException {
        if(savedData == null)
            throw new EmptyCacheException("В кэше нет данных для разделения.");

        savedHumans.clear();
        savedBarrels.clear();
        savedAnimals.clear();

        for (Sortable item : savedData) {
            if (item instanceof Animal) {
                savedAnimals.add((Animal) item);
            } else if (item instanceof Barrel) {
                savedBarrels.add((Barrel) item);
            } else if (item instanceof Human) {
                savedHumans.add((Human) item);
            }
        }
    }

    private void generateInstances(List<Sortable> instances, int count, Supplier<Sortable> generator) {
        for (int j = 0; j < count; j++) {
            instances.add(generator.get());
        }
    }
}