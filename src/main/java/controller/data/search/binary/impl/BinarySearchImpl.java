package controller.data.search.binary.impl;

import controller.data.DataController;
import controller.data.comparator.GeneralComparatorUtil;
import controller.data.impl.DataControllerImpl;
import controller.data.search.binary.exception.EmptyCacheException;
import model.entity.Animal;
import model.entity.sortable.Sortable;
import model.constants.EntityPatternsRegex;

import java.util.Comparator;
import java.util.List;

public class BinarySearchImpl<T>
//        implements BinarySearch<Sortable>
{

    private final DataController dataController;

    public BinarySearchImpl() {
        this.dataController = new DataControllerImpl();
    }

    // TODO: Task on 27.10
//    @Override
    public Sortable findEntity(EntityPatternsRegex entityPattern) throws EmptyCacheException {
        Sortable result = null;
        if (dataController.cacheIsClear()) throw new EmptyCacheException("Кэш приложения пуст.");
        switch (entityPattern) {
            case BARREL_PATTERN -> {
            }
            case ANIMAL_PATTERN -> {
            }
            case HUMAN_PATTERN -> {
            }
        }
        return result;
    }

//    @Override
    public List<Sortable> findByField(String field) {

        return null;
    }

//    @Override
    public int binarySearchBySpecies(List<T> allSortableEntities, T targetSortableEntity, Comparator<? super T> comparator) {
        int left = 0;
        int right = allSortableEntities.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midEntity = allSortableEntities.get(mid);

            int comparison = comparator.compare(midEntity, targetSortableEntity);
            if (comparison == 0) {
                return mid; // Найдено
            } else if (comparison < 0) {
                left = mid + 1; // Ищем в правой половине
            } else {
                right = mid - 1; // Ищем в левой половине
            }
        }

        return -1; // Не найдено
    }
}

class Test {

    public static void main(String[] args) {
        BinarySearchImpl<Sortable> binarySearch = new BinarySearchImpl<>();

        int i = binarySearch.binarySearchBySpecies(List.of(
                new Animal.Builder()
                        .species("a")
                        .eyeColor("a")
                        .hair(true)
                        .build(),
                new Animal.Builder()
                        .species("a")
                        .eyeColor("b")
                        .hair(true)
                        .build(),
                new Animal.Builder()
                        .species("b")
                        .eyeColor("a")
                        .hair(false)
                        .build(),
                new Animal.Builder()
                        .species("b")
                        .eyeColor("b")
                        .hair(false)
                        .build()
        ), new Animal.Builder()
                .species("b")
                .eyeColor("a")
                .hair(false)
                .build(),
                GeneralComparatorUtil.getComparatorForSortableEntity());
        System.out.println(i);
    }
}
