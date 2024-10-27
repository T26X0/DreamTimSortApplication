package controller.data.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGeneratorUtil {

    public static List<Integer> generateParts(int N) {

        Random rand = new Random();

        int part1 = rand.nextInt(N - 2) + 1;
        int part2 = rand.nextInt(N - part1 - 1) + 1;
        int part3 = N - part1 - part2;

        List<Integer> parts = new ArrayList<>();
        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

        return parts;
    }
}
