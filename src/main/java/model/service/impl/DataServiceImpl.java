package model.service.impl;

import model.repository.DataDAO;
import model.repository.impl.FileDAOImpl;
import model.service.DataService;

import java.util.List;

public class DataServiceImpl implements DataService {

    private final DataDAO dataDAO;

    public DataServiceImpl() {
        dataDAO = new FileDAOImpl();
    }

    @Override
    public String getData() {
        return dataDAO.getData();
    }

    @Override
    public void saveDataToLocalFile(String dataForSave, String fileName) {
        dataDAO.saveDataToLocalFile(dataForSave, fileName);
    }

    @Override
    public void clearDataFromLocalDirectory() {
        dataDAO.clearDataFromLocalDirectory();
    }
}
