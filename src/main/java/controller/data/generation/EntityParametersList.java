package controller.data.generation;

import java.util.List;

public interface EntityParametersList {
    interface AnimalList{
        List<String> species = List.of("Cat", "Dog", "Donkey", "Goat", "Horse", "Pig",
                "Rabbit", "Alligator", "Antelope", "Monkey", "Badger", "Basilisk", "Beaver", "Bee", "Bird",
                "Lynx", "Bonobo", "Mina", "Bovid", "Butterfly", "Buzzard", "Camel", "Water", "Porpoise", "Cardinal");

        List<String> eyeColor = List.of("Black", "Blue", "Brown", "Gray", "Green", "Hazel");
    }

    interface HumanList{
        List<String> SecondName = List.of("Klyuev", "Noskov", "Ozerov", "Koltsov", "Komissarov",
                "Merkulov", "Kireev", "Khomyakov", "Bulatov", "Ananev", "Burov", "Shaposhnikov", "Druzhinin",
                "Ostrovskii", "Shevelev", "Dolgov", "Suslov", "Shevtsov", "Pastukhov", "Rubtsov");
    }

    interface BarrelList{
        List<String> materialBarrel = List.of("Wood", "Metal", "Plastic");

        List<String>  storedMaterial = List.of("Water", "Alcohol", "Powder", "Meat", "Fish", "Honey");
    }
}
