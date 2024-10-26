package controller.data.impl;

public class StringProcessor {

    public static String[] process(String inputString) {

        String[] resultArray = inputString.split(";");

        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultArray[i].trim();
        }
        return resultArray;
    }
}
