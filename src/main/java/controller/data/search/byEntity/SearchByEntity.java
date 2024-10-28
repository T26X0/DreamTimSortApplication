package controller.data.search.byEntity;

import controller.data.search.binary.exception.EmptyCacheException;

import java.util.List;


public interface SearchByEntity<T> {

        int findByEntity(T entity) throws EmptyCacheException;

        List<T> findByField(String field) throws EmptyCacheException;
}
