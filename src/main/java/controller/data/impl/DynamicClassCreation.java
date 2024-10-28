package controller.data.impl;

import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;

import java.util.ArrayList;
import java.util.List;

public class DynamicClassCreation {

    public List<Sortable> creatureClass(String[] validatedData) {
        List<Sortable> entities = new ArrayList<>();

        for (String data : validatedData) {
            if (data.startsWith("human")) {
                Human human = createHuman(data);
                entities.add(human);
            } else if (data.startsWith("animal")) {
                Animal animal = createAnimal(data);
                entities.add(animal);
            } else if (data.startsWith("barrel")) {
                Barrel barrel = createBarrel(data);
                entities.add(barrel);
            }
        }

        return entities;
    }

    private Human createHuman(String data) {

        String[] parts = extractInnerData(data);

        int age = Integer.parseInt(parts[1].trim());
        String gender = parts[0].trim();
        String surname = parts[2].trim();

        return new Human.Builder()
                .age(age)
                .gender(gender)
                .surname(surname)
                .build();
    }

    private Animal createAnimal(String data) {

        String[] parts = extractInnerData(data);

        String species = parts[0].trim();
        String eyeColor = parts[1].trim();
        boolean hair = "yes".equalsIgnoreCase(parts[2].trim());

        return new Animal.Builder()
                .species(species)
                .eyeColor(eyeColor)
                .hair(hair)
                .build();
    }

    private Barrel createBarrel(String data) {

        String[] parts = extractInnerData(data);

        int value = Integer.parseInt(parts[0].trim());
        String storedMaterial = parts[1].trim();
        String materialOfManufacture = parts[2].trim();

        return new Barrel.Builder()
                .value(value)
                .storedMaterial(storedMaterial)
                .materialOfManufacture(materialOfManufacture)
                .build();
    }

    private String[] extractInnerData(String data) {
        String innerData = data.substring(data.indexOf("(") + 1, data.length() - 1);
        return innerData.split(",");
    }

}
