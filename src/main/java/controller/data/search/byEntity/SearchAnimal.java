package controller.data.search.byEntity;

import model.entity.Animal;

import java.util.List;

public interface SearchAnimal {

    List<Animal> findAllBySpecies(String species);
    List<Animal> findAllByEyeColor(String eyeColor);
    List<Animal> findAllByHair(boolean hair);
}
