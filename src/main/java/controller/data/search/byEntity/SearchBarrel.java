package controller.data.search.byEntity;

import model.entity.Barrel;

import java.util.List;

public interface SearchBarrel {

    List<Barrel> findAllByValue(int value);

    List<Barrel> findAllByStoredMaterial(String storeMaterial);

    List<Barrel> findAllByMaterialOfManufacture(String materialOfManufacture);
}
