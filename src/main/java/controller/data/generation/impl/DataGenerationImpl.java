package controller.data.generation.impl;

import controller.data.generation.DataGeneration;
import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;
import controller.data.generation.EntityParametersList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DataGenerationImpl implements DataGeneration {

    private List <Sortable> listRandomClass;
    private final Random selectRandomClass = new Random();

    private Animal getRandomAnimal() {
        Animal.Builder animalBulder = new Animal.Builder();
        animalBulder.species(EntityParametersList.AnimalList.species
                .get(selectRandomClass.nextInt(EntityParametersList.AnimalList.species.size())));
        animalBulder.eyeColor(EntityParametersList.AnimalList.eyeColor
                .get(selectRandomClass.nextInt(EntityParametersList.AnimalList.eyeColor.size())));
        animalBulder.hair(selectRandomClass.nextBoolean());
        return animalBulder.build();
    }

    private Barrel getRandomBarrel() {
        Barrel.Builder barrelBulder = new Barrel.Builder();
        barrelBulder.value(selectRandomClass.nextInt(251));
        barrelBulder.storedMaterial(EntityParametersList.BarrelList.storedMaterial
                .get(selectRandomClass.nextInt(EntityParametersList.BarrelList.storedMaterial.size())));
        barrelBulder.materialOfManufacture(EntityParametersList.BarrelList.materialBarrel
                .get(selectRandomClass.nextInt(EntityParametersList.BarrelList.materialBarrel.size())));
        return barrelBulder.build();
    }

    private Human getRandomHuman() {
        Human.Builder humanBulder = new Human.Builder();
        humanBulder.age(selectRandomClass.nextInt(101));
        humanBulder.gender(selectRandomClass.nextBoolean() ? "male" : "female");
        humanBulder.surname(EntityParametersList.HumanList.SecondName
                .get(selectRandomClass.nextInt(EntityParametersList.HumanList.SecondName.size())));
        return humanBulder.build();
    }

    @Override
    public List<Sortable> getRandomClassList(int len){

        listRandomClass = new ArrayList<>(len);

        for(int i = 0; i < len; i++){
            switch (selectRandomClass.nextInt(3)){
                case (0):
                    listRandomClass.add(getRandomAnimal());
                case (1):
                    listRandomClass.add(getRandomBarrel());
                case (2):
                    listRandomClass.add(getRandomHuman());
            }
        }
        return listRandomClass;
    }
}
