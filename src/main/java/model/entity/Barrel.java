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
        return 0;
    }
}
