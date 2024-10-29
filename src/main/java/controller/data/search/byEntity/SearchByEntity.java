package controller.data.search.byEntity;

import controller.data.search.binary.exception.EmptyCacheException;
import model.entity.sortable.Sortable;

import java.util.List;


public interface SearchByEntity<T> {

        List<T> findByField(List<T> allData, String field);
}
