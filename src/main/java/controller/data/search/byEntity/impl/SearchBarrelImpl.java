package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchBarrel;
import model.entity.Barrel;

import java.util.List;

public class SearchBarrelImpl implements SearchBarrel {

    @Override
    public List<Barrel> findAllByValue(int value) {
        return null;
    }

    @Override
    public List<Barrel> findAllByStoredMaterial(String storeMaterial) {
        return null;
    }

    @Override
    public List<Barrel> findAllByMaterialOfManufacture(String materialOfManufacture) {
        return null;
    }
}
