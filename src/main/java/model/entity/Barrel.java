package model.entity;

import lombok.Builder;
import lombok.Data;
import model.entity.sortable.Sortable;

@Data
@Builder
public class Barrel implements Sortable {

    private final int value;

    private final String storedMaterial;

    private final String materialOfManufacture;

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Barrel) return 0;
        return -1;
    }
}
