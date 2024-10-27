package controller.data.search.byEntity;

import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.List;

public interface SearchHuman {

    List<Human> findAllByAge(List<Sortable> searchList, int age);
    List<Human> findAllByGender(List<Sortable> searchList, String gender);
    List<Human> findAllBySurname(List<Sortable> searchList, String surname);
}
