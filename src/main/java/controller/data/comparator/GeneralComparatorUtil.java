package controller.data.comparator;

import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.Comparator;

public final class GeneralComparatorUtil {

    public static Comparator<Sortable> getComparatorForSortableEntity() {

        return new Comparator<Sortable>() {
            @Override
            public int compare(Sortable o1, Sortable o2) {
                if (o1 instanceof Barrel & o2 instanceof Barrel) {
                    return new BarrelComparator().compare((Barrel) o1, (Barrel) o2);
                }
                if (o1 instanceof Animal & o2 instanceof Animal) {
                    return new AnimalComparator().compare((Animal) o1, (Animal) o2);
                }
                if (o1 instanceof Human & o2 instanceof Human) {
                    return new HumanComparator().compare((Human) o1, (Human) o2);
                }

                if (o1 instanceof Animal & (o2 instanceof Barrel || o2 instanceof Human)) {
                    return -1;
                }
                if (o1 instanceof Barrel & (o2 instanceof Animal)) {
                    return 1;
                }
                if (o1 instanceof Barrel & (o2 instanceof Human)) {
                    return -1;
                }
                if (o1 instanceof Human & (o2 instanceof Barrel)) {
                    return 1;
                }
                if (o1 instanceof Human & (o2 instanceof Animal)) {
                    return 1;
                }
                return 1;
            }
        };
    }
}
