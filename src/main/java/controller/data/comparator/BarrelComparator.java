package controller.data.comparator;

import model.entity.Barrel;

import java.util.Comparator;

/**
 * <b>Сравнивает бочки в следующем порядке:</b>
 * <pre>
 *     Объем (value) в порядке возрастания, если объем равен, то сравнивает
 *     Хранимый материал (storedMaterial) по алфавиту, если материалы равны, то сравнивает
 *     Материал производства (materialOfManufacture), по алфавиту
 * </pre>
 */
public class BarrelComparator implements Comparator<Barrel> {

    @Override
    public int compare(Barrel o1, Barrel o2) {
        int valueCompare = o1.getValue() - (o2.getValue());
        int storedMaterialCompare = o1.getStoredMaterial().compareTo(o2.getStoredMaterial());
        int materialOfManufactureCompare = o1.getMaterialOfManufacture().compareTo(o2.getMaterialOfManufacture());
        if (valueCompare == 0) {
            if (storedMaterialCompare == 0) {
                return materialOfManufactureCompare;
            }
            return storedMaterialCompare;
        }
        return valueCompare;
    }
}
