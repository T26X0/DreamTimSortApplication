package model.repository.impl;

import model.repository.DataRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import model.repository.exception.NoSuchFileException;

public class DataRepositoryImpl implements DataRepository {

    @Override

    public String getData() throws NoSuchFileException {
        StringBuilder data = new StringBuilder();

        InputStream fileStream = getClass().getResourceAsStream("/file.txt");

        if (fileStream == null) { // Проверка, найден ли файл
            throw new NoSuchFileException("Файл /file.txt не найден в ресурсах.");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {
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
