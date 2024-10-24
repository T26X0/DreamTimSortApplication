package model.entity;

import lombok.Getter;
import model.entity.sortable.Sortable;

public class Barrel implements Sortable {

    @Getter
    private final int value;

    @Getter
    private final String storedMaterial;

    @Getter
    private final String materialOfManufacture;

    private Barrel(Builder builder) {
        this.value = builder.value;
        this.storedMaterial = builder.storedMaterial;
        this.materialOfManufacture = builder.materialOfManufacture;
    }

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Barrel) return 0;
        return -1;
    }

    public static class Builder {

        private int value;

        private String storedMaterial;

        private String materialOfManufacture;

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Builder storedMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder materialOfManufacture(String materialOfManufacture) {
            this.materialOfManufacture = materialOfManufacture;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }
}