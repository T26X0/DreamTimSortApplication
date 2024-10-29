package controller.data.search.byEntity;

import controller.data.search.binary.exception.EmptyCacheException;
import model.entity.sortable.Sortable;

import java.util.List;


public interface SearchByEntity<T> {

        int findByEntity(List<T> allData, T entity) throws EmptyCacheException;

        List<T> findByField(List<T> allData, String field) throws EmptyCacheException;
}
