package controller.data.search.binary.impl;

import controller.data.search.binary.BinarySearch;

import java.util.Comparator;
import java.util.List;

public class BinarySearchImpl<T>
        implements BinarySearch<T>
{


    @Override
    public int binarySearchByEntity(List<T> allSortableEntities,
                                    T targetSortableEntity,
                                    Comparator<T> comparator) {
        int left = 0;
        int right = allSortableEntities.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midEntity = allSortableEntities.get(mid);

            int comparison = comparator.compare(midEntity, targetSortableEntity);
            if (comparison == 0) {
                return mid; // Найдено
            } else if (comparison < 0) {
                left = mid + 1; // Ищем в правой половине
            } else {
                right = mid - 1; // Ищем в левой половине
            }
        }

        return -1; // Не найдено
    }
}
