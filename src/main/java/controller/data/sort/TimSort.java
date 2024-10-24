package controller.data.sort;

import controller.data.comparator.GeneralComparatorUtil;
import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimSort {

    private static final int RUN = 32;

    /**
     * Основной метод сортировки
     */
    public static <T> void timSort(List<T> unsortedList, Comparator<? super T> comparator) {
        int unsortedListSize = unsortedList.size();

        // Сортируем подмассивы (run) с помощью Insertion Sort
        for (int i = 0; i < unsortedListSize; i += RUN) {
            insertionSort(unsortedList, i, Math.min((i + RUN - 1), (unsortedListSize - 1)), comparator);
        }

        // Объединяем отсортированные подмассивы
        for (int size = RUN; size < unsortedListSize; size = 2 * size) {
            for (int left = 0; left < unsortedListSize; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (unsortedListSize - 1));

                if (mid < right) {
                    merge(unsortedList, left, mid, right, comparator);
                }
            }
        }
    }

    /**
     * Реализация Insertion Sort для сортировки подмассива от left до right.
     */
    private static <T> void insertionSort(List<T> array, int left, int right, Comparator<? super T> unsortedSubList) {
        for (int i = left + 1; i <= right; i++) {
            T temp = array.get(i);
            int j = i - 1;
            int compare = unsortedSubList.compare(array.get(j), temp);
            while (j >= left && compare > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, temp);
        }
    }

    /**
     * Объединение двух отсортированных подмассивов.
     * Первый подмассив: array[left..mid]
     * Второй подмассив: array[mid+1..right]
     */
    private static <T> void merge(List<T> array, int left, int mid, int right, Comparator<? super T> sortedList) {
        // Размеры двух подмассивов для слияния
        int len1 = mid - left + 1;
        int len2 = right - mid;

        // Создаем временные массивы для слияния
        Object[] leftArray = new Object[len1];
        Object[] rightArray = new Object[len2];

        for (int x = 0; x < len1; x++) {
            leftArray[x] = array.get(left + x);
        }
        for (int x = 0; x < len2; x++) {
            rightArray[x] = array.get(mid + 1 + x);
        }

        // Индексы для временных массивов
        int i = 0, j = 0;
        int k = left;

        // Сливаем временные массивы обратно в array[left..right]
        while (i < len1 && j < len2) {
            if (sortedList.compare((T) leftArray[i], (T) rightArray[j]) <= 0) {
                array.set(k, (T) leftArray[i]);
                i++;
            } else {
                array.set(k, (T) rightArray[j]);
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы, если таковые имеются
        while (i < len1) {
            array.set(k, (T) leftArray[i]);
            i++;
            k++;
        }

        while (j < len2) {
            array.set(k, (T) rightArray[j]);
            j++;
            k++;
        }
    }
}

class TimSortTest {

    public static void main(String[] args) {
        List<Sortable> unsortedList = new ArrayList<>();
        unsortedList.add(
                Animal.builder()
                        .species("b")
                        .eyeColor("b")
                        .hair(true)
                        .build()
        );
        unsortedList.add(
                Animal.builder()
                        .species("b")
                        .eyeColor("a")
                        .hair(false)
                        .build()
        );
        unsortedList.add(
                Human.builder()
                        .age(21)
                        .gender("male")
                        .surname("ivanov")
                        .build()
        );
        unsortedList.add(
                Barrel.builder()
                        .value(50)
                        .storedMaterial("wood")
                        .materialOfManufacture("metal")
                        .build()
        );
        unsortedList.add(
                Animal.builder()
                        .species("b")
                        .eyeColor("a")
                        .hair(true)
                        .build()
        );
        unsortedList.forEach(System.out::println);
        TimSort.timSort(unsortedList, GeneralComparatorUtil.getComparatorForSortableEntity());
        System.out.println("********");
        unsortedList.forEach(System.out::println);

    }
}
