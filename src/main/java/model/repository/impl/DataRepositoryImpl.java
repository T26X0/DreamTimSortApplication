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
                System.out.println("Искомый файл не был найден. Директория обновлена и создан новый файл для записи данных. Можете заполнять и вернуться сюда снова после заполнения файла.");
            } catch (Exception e) {
                e.printStackTrace(); // Логирование исключения
            }
            return ""; // Возвращаем пустую строку, так как файл не существовал
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line.trim()).append("\n"); // Чтение строки с файла и удаление лишних пробелов
            }

        } catch (IOException e) {
            e.printStackTrace(); // Логирование исключения
        }
        return data.toString(); //Возвращение итоговой строки
    }
}
