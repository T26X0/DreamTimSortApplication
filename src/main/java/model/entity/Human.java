package model.entity;

import lombok.Getter;
import model.entity.sortable.Sortable;

public class Human implements Sortable {

    @Getter
    private final int age;

    @Getter
    private final String gender;

    @Getter
    private final String surname;

    private Human(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.surname = builder.surname;
    }

    @Override
    public int compareTo(Sortable o) {
        if (o instanceof Human) return 0;
        return 1;
    }

    public static class Builder {

        private int age;

        private String gender;

        private String surname;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }

    public boolean isContainsField(String fieldName);
}