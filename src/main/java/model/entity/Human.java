package model.entity;

import lombok.Builder;
import lombok.Data;
import model.entity.sortable.Sortable;

@Data
@Builder
public class Human implements Sortable {

    private final String gender;

    private final int age;

    private final String surname;

    @Override
    public int compareTo(Sortable o) {
        return 0;
    }
}
