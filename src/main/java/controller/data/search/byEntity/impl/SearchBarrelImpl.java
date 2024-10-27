package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchBarrel;
import model.entity.Barrel;
import model.entity.sortable.Sortable;

import java.util.List;

public class SearchBarrelImpl implements SearchBarrel {

    @Override
    public List<Barrel> findAllByValue(List<Sortable> searchList, int value) {
        return null;
    }

    @Override
    public List<Barrel> findAllByStoredMaterial(List<Sortable> searchList, String storeMaterial) {
        return null;
    }

    @Override
    public List<Barrel> findAllByMaterialOfManufacture(List<Sortable> searchList, String materialOfManufacture) {
        return null;
    }
}
