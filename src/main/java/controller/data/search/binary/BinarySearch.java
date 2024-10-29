package controller.data.search.binary;

import java.util.Comparator;
import java.util.List;

public interface BinarySearch<T> {

    int binarySearchByEntity(List<T> allSortableEntities, T targetSortableEntity, Comparator<T> comparator);
}
