package controller.data.generation;

import java.util.List;

public interface EntityParametersList {
    interface AnimalList{
        List<String> species = List.of("cat", "dog", "donkey", "goat", "Horse", "pig",
                "rabbit", "alligator", "antelope", "monkey", "badger", "basilisk", "beaver", "bee", "bird",
                "lynx", "bonobo", "mina", "bovid", "butterfly", "buzzard", "camel", "water", "porpoise", "cardinal");

        List<String> eyeColor = List.of("black", "blue", "brown", "gray", "green", "hazel");
    }

    interface HumanList{
        List<String> SecondName = List.of("klyuev", "noskov", "ozerov", "koltsov", "komissarov",
                "merkulov", "kireev", "khomyakov", "bulatov", "ananev", "burov", "shaposhnikov", "druzhinin",
                "ostrovskii", "shevelev", "dolgov", "suslov", "shevtsov", "pastukhov", "rubtsov");
    }

    interface BarrelList{
        List<String> materialBarrel = List.of("wood", "metal", "plastic");

        List<String>  storedMaterial = List.of("water", "alcohol", "powder", "meat", "fish", "honey");
    }
}
