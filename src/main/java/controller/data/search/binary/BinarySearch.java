package controller.data.search.binary;

import controller.data.search.binary.exception.EmptyCacheException;
import model.entity.sortable.Sortable;
import model.repository.constants.EntityPatternsRegex;

import java.util.Comparator;
import java.util.List;

public interface BinarySearch<T> {

    T findEntity(EntityPatternsRegex entityPattern) throws EmptyCacheException;

    List<T> findByField(String field);

    int binarySearchBySpecies(List<T> allSortableEntities, T targetSortableEntity, Comparator<? super T> unsortedSubList);
}
