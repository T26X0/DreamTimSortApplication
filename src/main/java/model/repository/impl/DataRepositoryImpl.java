package model.repository.impl;

import model.repository.DataRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.repository.exception.NoSuchFileException;

public class DataRepositoryImpl implements DataRepository {

    private static final String FILE_PATH = "src/main/resources/file.txt";

    @Override
    public String getData() throws NoSuchFileException {
        StringBuilder data = new StringBuilder();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
                System.out.println("Искомый файл не найден. Директория успешно обновлена, и создан новый файл для записи данных. Вы можете заполнить его и вернуться сюда для продолжения работы после заполнения.");
            } catch (Exception e) {
                e.printStackTrace(); // Логирование исключения
            }
            return ""; // Возвращаем пустую строку, так как файл не существовал
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Проверка, на пустую строку
                    data.append(line.trim()).append("\n"); // Чтение строки с файла и удаление лишних пробелов
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Логирование исключения
        }

        // Если файл пустой, ничего не возвращаем
        if (data.toString().trim().isEmpty()) {
            System.out.println("Файл пуст, данных для отображения нет.");
            return "";
        }

        return data.toString(); //Возвращение итоговой строки
    }
}
