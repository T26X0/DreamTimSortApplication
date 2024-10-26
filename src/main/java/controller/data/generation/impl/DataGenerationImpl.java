package controller.data.generation.impl;

import controller.data.generation.DataGeneration;
import model.entity.Animal;
import model.entity.Barrel;
import model.entity.Human;
import model.entity.sortable.Sortable;
import controller.data.EntityParametrsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DataGenerationImpl implements DataGeneration {

    private List <Sortable> listRandomClass;
    private final Random selectRandomClass = new Random();

    private Animal getRandomAnimal() {
        Animal.Builder animalBulder = new Animal.Builder();
        animalBulder.species();
        animalBulder.eyeColor();
        animalBulder.hair();
        return animalBulder.build();
    }

    private Barrel getRandomBarrel() {
        Barrel.Builder barrelBulder = new Barrel.Builder();
        return barrelBulder.build();
    }

    private Human getRandomHuman() {
        Human.Builder humanBulder = new Human.Builder();

        return humanBulder.build();
    }

    @Override
    public List<Sortable> getRandomClassList(int len){

        listRandomClass = new ArrayList<>(len);

        for(int i = 0; i < len; i++){
            switch (selRanClass.nextInt(3)){
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
