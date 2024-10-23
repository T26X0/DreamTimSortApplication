package model.impl;

import model.DataService;
import model.repository.DataRepository;
import model.repository.impl.DataRepositoryImpl;

public class DataServiceImpl implements DataService {

    private final DataRepository repository;

    public DataServiceImpl() {
        repository = new DataRepositoryImpl();
    }

    @Override
    public String getData() {
        return repository.getData();
    }
}
