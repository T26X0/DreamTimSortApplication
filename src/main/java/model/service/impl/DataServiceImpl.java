package model.service.impl;

import model.DAO.DataDAO;
import model.DAO.impl.FileDAOImpl;
import model.service.DataService;

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
