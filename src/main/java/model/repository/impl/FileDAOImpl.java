package model.repository.impl;

import controller.user.UserController;
import model.repository.DataDAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.repository.exception.NoSuchFileException;

public class FileDAOImpl implements DataDAO {

    private final String FILE_PATH = "src/main/resources/file.txt";

    @Override
    public String getData() throws NoSuchFileException {
        StringBuilder dataFromFile = new StringBuilder();

        boolean fileWasHasCreated = createFileIfNotExist();

        if (fileWasHasCreated) {
            return dataFromFile.toString();
        }
        readFileInto(dataFromFile);
        return dataFromFile.toString();
    }

    /**
     * Возвращает true если в процессе проверки файла не оказалось и пришлось его создавать
     */
    private boolean createFileIfNotExist() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
                UserController.addException(new NoSuchFileException("Искомый файл не найден. Директория успешно обновлена, и создан новый файл для записи данных. Вы можете заполнить его и вернуться сюда для продолжения работы после заполнения."));
                return true;
            } catch (IOException e) {
                // TODO: Если не получилось создать файл (ошибка на все приложение)
                UserController.addException(new IOException("Ошибка чтения файла. Попробуйте обновить/сбросить директорию"));
                return false;
            }
        }
        return false;
    }

    private void readFileInto(StringBuilder dataFromFile) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Проверка, на пустую строку
                if (!line.trim().isEmpty()) {
                    // Чтение строки с файла и удаление лишних пробелов
                    dataFromFile.append(line.trim()).append("\n");
                }
            }
        } catch (IOException e) {
            UserController.addException(new IOException("Ошибка чтения файла. Попробуйте обновить/сбросить директорию"));
        }
    }
}