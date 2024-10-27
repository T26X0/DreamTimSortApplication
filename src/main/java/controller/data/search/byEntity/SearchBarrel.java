package controller.data.search.byEntity;

import model.entity.Barrel;
import model.entity.sortable.Sortable;


import java.util.List;

public interface SearchBarrel {

    List<Barrel> findAllByValue(List<Sortable> searchList, int value);

    List<Barrel> findAllByStoredMaterial(List<Sortable> searchList, String storeMaterial);

    List<Barrel> findAllByMaterialOfManufacture(List<Sortable> searchList, String materialOfManufacture);
}
