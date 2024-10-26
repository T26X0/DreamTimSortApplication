package controller.data.search.byEntity;

import model.entity.Human;

import java.util.List;

public interface SearchHuman {

    List<Human> findAllByAge(int age);
    List<Human> findAllByGender(String gender);
    List<Human> findAllBySurname(String surname);
}
