package controller.data.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.generation.DataGeneration;
import controller.data.generation.impl.DataGenerationImpl;
import controller.data.sort.TimSort;
import controller.data.sort.impl.TimSortImpl;

import lombok.Getter;
import lombok.Setter;
import model.constants.Entities;
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

    private final TimSort<Sortable> timSort;

    public DataControllerImpl() {

        this.dataService = new DataServiceImpl();
        this.timSort = new TimSortImpl<>();
    }

    @Override
    public <T extends Sortable> List<T> getListEntities(Entities entities) {
        switch (entities) {
            case ANIMAL -> {
                return getDataFromCache().stream()
                        .filter(it -> it instanceof Animal)
                        .map(it -> (T) it).toList();
            }
            case BARREL -> {
                return getDataFromCache().stream()
                        .filter(it -> it instanceof Barrel)
                        .map(it -> (T) it).toList();
            }
            case HUMAN -> {
                return getDataFromCache().stream()
                        .filter(it -> it instanceof Human)
                        .map(it -> (T) it).toList();
            }
            default -> {
                return null;
            }
        }
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

        List<Sortable> sortables = timSort.sort(cache, GeneralComparatorUtil.getComparatorForSortableEntity());
        setCache(sortables);
        separateCacheToUniqueLists();

        return cache;
    }

    @Override
    public void saveDataInCache(List<Sortable> listData) {

        cache = List.copyOf(listData);
    }

    @Override
    public void saveCacheInLocalFile() {
        // TODO: fileName в константы
        String dataForSave = StringProcessor.formatAsString(getDataFromCache());
        dataService.saveDataToLocalFile(dataForSave, "all_sorted_data");
    }

    @Override
    public void saveCacheInLocalFileByEntities() {
        List<Sortable> savedAnimals = formatToSortable(getSavedAnimals());
        String animalsForSave = StringProcessor.formatAsString(savedAnimals);

        List<Sortable> savedBarrels = formatToSortable(getSavedBarrels());
        String barrelsForSave = StringProcessor.formatAsString(savedBarrels);

        List<Sortable> savedHumans = formatToSortable(getSavedHumans());
        String humansForSave = StringProcessor.formatAsString(savedHumans);

        dataService.saveDataToLocalFile(animalsForSave, "saved_animals");
        dataService.saveDataToLocalFile(barrelsForSave, "saved_barrels");
        dataService.saveDataToLocalFile(humansForSave, "saved_humans");
    }

    private <T> List<Sortable> formatToSortable(List<T> list) {
        return list.stream().map(it -> (Sortable) it).toList();
    }

    @Override
    public void clearCache() {

        cache = null;
    }

    @Override
    public boolean cacheIsNotClear() {

        if (Objects.nonNull(cache)) {
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

    private void separateCacheToUniqueLists() {

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