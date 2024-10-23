package model.entity;

import lombok.Builder;
import lombok.Data;
import model.entity.sortable.Sortable;

import java.util.Comparator;

@Data
@Builder
public class Animal implements Sortable {

    private final String species;

    private final String eyeColor;

    private final boolean hair;

    @Override
    public int compareTo(Sortable o) {
        return 0;
    }
}
