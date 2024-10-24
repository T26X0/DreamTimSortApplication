package model.entity;

import lombok.Builder;
import lombok.Data;
import model.entity.sortable.Sortable;


@Data
@Builder
public class Animal implements Sortable {

    private final String species;

    private final String eyeColor;

    private final boolean hair;

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Animal) return 0;
        if (o instanceof Barrel) return 1;
        return -1;
    }
}
