package controller.data.comparator;

import model.entity.Animal;

import java.util.Comparator;

/**
 * <b>Сравнивает животных в следующем порядке:</b>
 * <pre>
 *     Вид (species) по алфавиту, если виды равны, то сравнивает
 *     Цвет глаз (eyeColor) по алфавиту, если цвета глаз равны, то сравнивает
 *     Шерсть (heir), волосатые вначале
 * </pre>
 */
public class AnimalComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        int speciesCompare = o1.getSpecies().compareTo(o2.getSpecies());
        int eyeColorCompare = o1.getEyeColor().compareTo(o2.getEyeColor());
        if (speciesCompare == 0) {
            if (eyeColorCompare == 0) {
                if (o1.isHair() == o2.isHair()) {
                    return 0;
                }
                if (o1.isHair()) {
                    return -1;
                }
                return 1;
            }
            return eyeColorCompare;
        }
        return speciesCompare;
    }
}
