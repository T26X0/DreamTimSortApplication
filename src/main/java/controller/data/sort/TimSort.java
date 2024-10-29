package controller.data.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimSort {

    private static final int RUN = 32;

    /**
     * Основной метод сортировки
     */
    public static <T> void timSort(List<T> immutableList, Comparator<? super T> comparator) {
        List<T> mutableList = new ArrayList<>(immutableList);
        int unsortedListSize = mutableList.size();

        // Сортируем подмассивы (run) с помощью Insertion Sort
        for (int i = 0; i < unsortedListSize; i += RUN) {
            insertionSort(mutableList, i, Math.min((i + RUN - 1), (unsortedListSize - 1)), comparator);
        }

        // Объединяем отсортированные подмассивы
        for (int size = RUN; size < unsortedListSize; size = 2 * size) {
            for (int left = 0; left < unsortedListSize; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (unsortedListSize - 1));

                if (mid < right) {
                    merge(mutableList, left, mid, right, comparator);
                }
            }
        }
    }

    /**
     * Сортировка вставками сортировки подмассива от left до right.
     */
    private static <T> void insertionSort(List<T> array, int left, int right, Comparator<? super T> unsortedSubList) {
        for (int i = left + 1; i <= right; i++) {
            T temp = array.get(i);
            int j = i - 1;
            while (j >= left && unsortedSubList.compare(array.get(j), temp) > 0) {
                System.out.println("array: " + array);
                System.out.println("j: " + j);
                System.out.println("array.get(j): " + array.get(j));
                T tempEntity = array.get(j);
                array.set(j + 1, tempEntity);
                j--;
            }
            array.set(j + 1, temp);
        }
    }

    /**
     * Объединение двух отсортированных подмассивов
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
