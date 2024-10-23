package model.repository.impl;

import model.repository.DataRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DataRepositoryImpl implements DataRepository {

    @Override
    public String getData() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/file.txt"), StandardCharsets.UTF_8))) {
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
