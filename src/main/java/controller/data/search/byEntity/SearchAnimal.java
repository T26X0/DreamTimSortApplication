package controller.data.search.byEntity;

import model.entity.Animal;
import model.entity.sortable.Sortable;

import java.util.List;

public interface SearchAnimal {


    List<Animal> findAllBySpecies(List<Sortable> searchList, String species);
    List<Animal> findAllByEyeColor(List<Sortable> searchList, String eyeColor);
    List<Animal> findAllByHair(List<Sortable> searchList, boolean hair);
}
