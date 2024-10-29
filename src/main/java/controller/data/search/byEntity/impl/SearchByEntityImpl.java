package controller.data.search.byEntity.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.impl.DataControllerImpl;
import controller.data.search.binary.exception.EmptyCacheException;
import controller.data.search.binary.impl.BinarySearchImpl;
import controller.data.search.byEntity.SearchAnimal;
import controller.data.search.byEntity.SearchBarrel;
import controller.data.search.byEntity.SearchByEntity;
import controller.data.search.byEntity.SearchHuman;
import model.entity.Animal;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.Comparator;
import java.util.List;

public class SearchByEntityImpl implements SearchByEntity<Sortable> {

    private final SearchBarrel searchBarrel;
    private final SearchAnimal searchAnimal;
    private final SearchHuman searchHuman;
    private final DataController dataController;

    public SearchByEntityImpl() {
        this.searchBarrel = new SearchBarrelImpl();
        this.searchAnimal = new SearchAnimalImpl();
        this.searchHuman = new SearchHumanImpl();
        this.dataController = new DataControllerImpl();
    }

    @Override
    public int findByEntity(List<Sortable> allData, Sortable entity) throws EmptyCacheException {
        return getIdByEntityFromList(allData, entity, GeneralComparatorUtil.getComparatorForSortableEntity());
    }

    @Override
    public List<Sortable> findByField(List<Sortable> allData, String field) throws EmptyCacheException {
        return allData.stream()
                .filter(it -> it.getAllFieldToStringList().contains(field))
                .toList();
    }

    private <T> int getIdByEntityFromList(List<Sortable> allData, Sortable soughtEntity, Comparator<Sortable> comparator) {
        for (int i = 0; i < allData.size(); i++) {
            Sortable temp = allData.get(i);
            int compare = comparator.compare(temp, soughtEntity);
            if (compare == 0) return i;
        }
        return -1;
    }
}
