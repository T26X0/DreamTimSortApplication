package controller.data.comparator;

import model.entity.Human;

import java.util.Comparator;


/**
 * <b>Сравнивает людей в следующем порядке:</b>
 * <pre>
 *     Возраст (age) в порядке возрастания, если возраст равен, то сравнивает
 *     Пол (gender) по алфавиту, если полы равны, то сравнивает
 *     Фамилию (surname), по алфавиту
 * </pre>
 */

public class HumanComparator implements Comparator<Human> {

    @Override
    public int compare(Human o1, Human o2) {
        int ageCompare = o1.getAge() - (o2.getAge());
        int genderCompare = o1.getGender().compareTo(o2.getGender());
        int surnameCompare = o1.getSurname().compareTo(o2.getSurname());
        if (ageCompare == 0) {
            if (genderCompare == 0) {
                return surnameCompare;
            }
            return genderCompare;
        }
        return ageCompare;
    }
}
