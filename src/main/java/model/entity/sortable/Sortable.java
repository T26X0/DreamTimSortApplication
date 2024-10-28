package model.entity.sortable;

import java.util.List;
/**
 * Маркерный интерфейс для обозначения сущностей, которые DataController может сортировать
 */
public interface Sortable extends Comparable<Sortable> {
    List<String> getAllFieldToStringList();
}
