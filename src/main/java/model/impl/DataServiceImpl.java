package model.impl;

import model.DataService;
import model.repository.DataRepository;
import model.repository.exception.NoSuchFileException;
import model.repository.impl.DataRepositoryImpl;

public class DataServiceImpl implements DataService {

    private final DataRepository repository;

    public DataServiceImpl() {
        repository = new DataRepositoryImpl();
    }

    @Override
    public String getData() {
        try {
            return repository.getData();
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
    }
}
