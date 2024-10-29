package controller.data.sort;

import java.util.Comparator;
import java.util.List;

public interface TimSort<T> {

    <T> List<T> sort(List<T> immutableList, Comparator<? super T> comparator);
}
