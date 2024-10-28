package model.entity;

import static model.constants.EntityConstants.HAS_NO_WOOL;
import static model.constants.EntityConstants.HAS_WOOL;

import lombok.Getter;
import model.entity.sortable.Sortable;

import java.util.Collections;
import java.util.List;

public class Animal implements Sortable {

    @Getter
    private final String species;

    @Getter
    private final String eyeColor;

    @Getter
    private final boolean hair;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hair = builder.hair;
    }

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Animal) return 0;
        if (o instanceof Barrel) return 1;
        return -1;
    }

    @Override
    public String toString() {
        return String.format("animal(%s,%s,%s);", species, eyeColor, hair ? HAS_WOOL.getValue() : HAS_NO_WOOL.getValue());
    }

    @Override
    public List<String> getAllFieldToStringList() {
        return List.of(species, eyeColor, (hair ? "yes" : "no"));
    }

    public static class Builder {

        private String species;

        private String eyeColor;

        private boolean hair;

        public Builder species(String species) {
            this.species = species;
            return this;
        }

        public Builder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder hair(boolean hair) {
            this.hair = hair;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}