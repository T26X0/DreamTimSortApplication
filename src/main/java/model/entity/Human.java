package model.entity;

import lombok.Builder;
import lombok.Data;
import model.entity.sortable.Sortable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class Human implements Sortable {

    private final int age;

    private final String gender;

    private final String surname;

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Human) return 0;
        return 1;
    }
}
