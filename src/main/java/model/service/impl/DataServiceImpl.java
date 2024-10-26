package model.service.impl;

import model.repository.DataDAO;
import model.repository.exception.NoSuchFileException;
import model.repository.impl.FileDAOImpl;
import model.service.DataService;

public class DataServiceImpl implements DataService {

    private final DataDAO dataDAO;

    public DataServiceImpl() {
        dataDAO = new FileDAOImpl();
    }

    @Override
    public String getData() {
        try {
            return dataDAO.getData();
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
    }
}
