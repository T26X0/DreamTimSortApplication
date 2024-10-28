package controller.data.impl;

import model.entity.sortable.Sortable;

import java.util.List;
import java.util.stream.Collectors;

public class StringProcessor {

    public static String[] process(String inputString) {

        String[] resultArray = inputString.split(";");

        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultArray[i].trim();
        }
        return resultArray;
    }

    public static String formatDirtyData(List<Sortable> dirtyData) {
        return dirtyData.stream()
                .map(Sortable::toString)
                .collect(Collectors.joining(" "))
                .replaceAll(";$", "");
    }
}
