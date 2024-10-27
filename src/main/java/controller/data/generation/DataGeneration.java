package controller.data.generation;

import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.List;

/**
 * Класс для генерации рандомных данных
 */
public interface DataGeneration {

    List<Sortable> getRandomClassList(int len);

}
