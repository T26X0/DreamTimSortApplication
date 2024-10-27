package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchHuman;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.List;

public class SearchHumanImpl implements SearchHuman {

    @Override
    public List<Human> findAllByAge(List<Sortable> searchList, int age) {
        return null;
    }

    @Override
    public List<Human> findAllByGender(List<Sortable> searchList, String gender) {
        return null;
    }

    @Override
    public List<Human> findAllBySurname(List<Sortable> searchList, String surname) {
        return null;
    }
}
