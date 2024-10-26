package controller.data.search.byEntity.impl;

import controller.data.search.byEntity.SearchAnimal;
import model.entity.Animal;

import java.util.List;

public class SearchAnimalImpl implements SearchAnimal {

    @Override
    public List<Animal> findAllBySpecies(String species) {
        return null;
    }

    @Override
    public List<Animal> findAllByEyeColor(String eyeColor) {
        return null;
    }

    @Override
    public List<Animal> findAllByHair(boolean hair) {
        return null;
    }
}
