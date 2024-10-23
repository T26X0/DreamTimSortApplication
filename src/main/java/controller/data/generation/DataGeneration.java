package controller.data.generation;

import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;

/**
 * Класс для генерации рандомных данных
 */
public interface DataGeneration {

    Animal getRandomAnimal();

    Barrel getRandomBarrel();

    Human getRandomHuman();
}
