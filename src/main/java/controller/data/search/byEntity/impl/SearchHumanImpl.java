package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchHuman;
import model.entity.Human;

import java.util.List;

public class SearchHumanImpl implements SearchHuman {

    @Override
    public List<Human> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<Human> findAllByGender(String gender) {
        return null;
    }

    @Override
    public List<Human> findAllBySurname(String surname) {
        return null;
    }
}
