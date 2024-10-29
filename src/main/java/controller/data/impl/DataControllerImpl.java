package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import controller.data.search.binary.exception.EmptyCacheException;
import controller.data.sort.TimSort;

import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;

import model.entity.sortable.Sortable;
import model.service.DataService;
import model.service.impl.DataServiceImpl;
import validation.exception.PatternMismatchException;
import validation.forData.DataValidator;
import validation.forData.IntegerValidator;
import validation.forData.impl.DataValidatorImpl;
import validation.forData.impl.IntegerValidatorImpl;
import validation.forEntities.EntityValidator;
import validation.forEntities.impl.EntityValidatorImpl;

import java.util.*;
import java.util.function.Supplier;

public class DataControllerImpl implements DataController {

    @Setter
    private List<Sortable> cache = null;
    @Getter
    private List<Animal> savedAnimals = new ArrayList<>();
    @Getter
    private List<Barrel> savedBarrels = new ArrayList<>();
    @Getter
    private List<Human> savedHumans = new ArrayList<>();

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
        if (data.isEmpty()) throw new PatternMismatchException("Исходный файл пуст.");
        saveDataInCache(convertStringToSortableList(data));
        return convertStringToSortableList(data);
    }

    @Override
    public List<Sortable> generateData(int limit) throws Exception {

        IntegerValidator integerValidator = new IntegerValidatorImpl();
        integerValidator.isPositive(limit);

        DataGeneration generatedData = new DataGenerationImpl();

        cache = generatedData.getRandomClassList(limit);
        return List.copyOf(cache);
    }

    @Override
    public List<Sortable> getDataFromCache() {

        return List.copyOf(cache);
    }

    @Override
    public List<Sortable> sortData() {

        List<Sortable> sortables = TimSort.timSort(cache, GeneralComparatorUtil.getComparatorForSortableEntity());
        setCache(sortables);
        return cache;
    }

    @Override
    public void saveDataInCache(List<Sortable> listData) {

        cache = List.copyOf(listData);
    }

    @Override
    public void clearCache() {

        cache = null;
    }

    @Override
    public boolean cacheIsClear() {

        if (Objects.isNull(cache)) {
            return true;
        }

        return cache.isEmpty();
    }

    @Override
    public void clearDataFromLocalDirectory() {
        dataService.clearDataFromLocalDirectory();
    }

    @Override
    public List<Sortable> convertStringToSortableList(String string) throws Exception {
        DataValidator dataValidator = new DataValidatorImpl();
        dataValidator.validateSourceString(string);

        String[] processedData = StringProcessor.process(string);

        EntityValidator entityValidator = new EntityValidatorImpl();

        for (String dataToValidate : processedData) {
            entityValidator.validationEntityString(dataToValidate);
        }

        DynamicClassCreation dynamicallyCreatedClasses = new DynamicClassCreation();
        return dynamicallyCreatedClasses.creatureClass(processedData);
    }

    public void saveDataToLocalFile() throws EmptyCacheException {

        separateCacheToUniqueLists();

        String animalString = getSavedAnimals().stream()
            .map(Animal::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(animalString, "sortedAnimals");

        String barrelString = getSavedBarrels().stream()
            .map(Barrel::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(barrelString, "sortedBarrels");

        String humanString = getSavedHumans().stream()
            .map(Human::toString)
            .collect(Collectors.joining(""));
        dataService.saveDataToLocalFile(humanString, "sortedHumans");
    }

    private void separateCacheToUniqueLists() throws EmptyCacheException {
        if(cache == null)
            throw new EmptyCacheException("В кэше нет данных для разделения.");

        savedHumans.clear();
        savedBarrels.clear();
        savedAnimals.clear();

        for (Sortable item : cache) {
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