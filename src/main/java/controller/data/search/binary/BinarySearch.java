package controller.data.search.binary;

import java.util.List;

public interface BinarySearch<T> {

    T findEntity(String entityPattern);

    List<T> findByField(String field);
}
