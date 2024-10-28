package controller.data.search.binary;

import controller.data.search.binary.exception.EmptyCacheException;
import model.constants.EntityPatternsRegex;
import model.entity.sortable.Sortable;

import java.util.Comparator;
import java.util.List;

public interface BinarySearch<T> {

    int binarySearchBySpecies(List<T> allSortableEntities, T targetSortableEntity, Comparator<T> comparator);
}
