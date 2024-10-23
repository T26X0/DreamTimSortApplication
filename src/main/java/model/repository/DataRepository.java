package model.repository;

import model.repository.exception.NoSuchFileException;

/**
 * Репозиторий читает данные из файла
 */
public interface DataRepository {

    String getData() throws NoSuchFileException;
}
