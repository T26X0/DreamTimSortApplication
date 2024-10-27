package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchAnimal;
import model.entity.Animal;
import model.entity.sortable.Sortable;

import java.util.List;

public class SearchAnimalImpl implements SearchAnimal {

    @Override
    public List<Animal> findAllBySpecies(List<Sortable> searchList, String species) {
        return null;
    }

    @Override
    public List<Animal> findAllByEyeColor(List<Sortable> searchList, String eyeColor) {
        return null;
    }

    @Override
    public List<Animal> findAllByHair(List<Sortable> searchList, boolean hair) {
        return null;
    }
}
