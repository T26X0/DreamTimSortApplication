package controller.data.search.binary.impl;

import controller.data.DataController;
import controller.data.impl.DataControllerImpl;
import controller.data.search.binary.BinarySearch;
import controller.data.search.binary.exception.EmptyCacheException;
import model.entity.sortable.Sortable;
import model.repository.constants.EntityPatternsRegex;

import java.util.List;
import java.util.Objects;

public class BinarySearchImpl implements BinarySearch<Sortable> {

    private final DataController dataController;

    public BinarySearchImpl() {
        this.dataController = new DataControllerImpl();
    }

    // TODO: Task on 27.10
    @Override
    public Sortable findEntity(EntityPatternsRegex entityPattern) throws EmptyCacheException {
        Sortable result = null;
        if (dataController.cacheIsClear()) throw new EmptyCacheException("Кэш приложения пуст.");
        switch (entityPattern) {
            case BARREL_PATTERN -> {
            }
            case ANIMAL_PATTERN -> {
            }
            case HUMAN_PATTERN -> {
            }
        }
        return result;
    }

    @Override
    public List<Sortable> findByField(String field) {

        return null;
    }
}
