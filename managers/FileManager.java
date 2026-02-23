package managers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.MusicBand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileManager {
    private String fileName;
    private Gson gson;

    public FileManager(String fileName) {
        this.fileName = fileName;
        
        // Настраиваем Gson, чтобы он красиво выводил JSON и умел работать с LocalDate
        this.gson = new GsonBuilder()
                .setPrettyPrinting() // Для красивого вывода с переносами строк
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }

    // Метод для чтения из файла
    public void load(CollectionManager collectionManager) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("ВНИМАНИЕ: Переменная окружения с именем файла не найдена!");
            return;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл " + fileName + " не найден. Будет создана пустая коллекция.");
            return;
        }
        if (!file.canRead()) {
            System.out.println("Ошибка: Нет прав на чтение файла " + fileName);
            return;
        }

        // По заданию читаем через java.util.Scanner
        try (Scanner fileScanner = new Scanner(file)) {
            StringBuilder jsonString = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                jsonString.append(fileScanner.nextLine());
            }

            if (jsonString.length() == 0) {
                System.out.println("Файл пуст. Коллекция будет пустой.");
                return;
            }

            // Указываем Gson'у, в какой именно тип данных нужно превратить строку JSON
            Type collectionType = new TypeToken<PriorityQueue<MusicBand>>() {}.getType();
            PriorityQueue<MusicBand> loadedCollection = gson.fromJson(jsonString.toString(), collectionType);

            if (loadedCollection != null) {
                for (MusicBand band : loadedCollection) {
                    collectionManager.addElement(band);
                }
                System.out.println("Коллекция успешно загружена! Загружено элементов: " + loadedCollection.size());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка синтаксиса JSON. Файл поврежден или содержит некорректные данные.");
        }
    }

    // Метод для записи в файл
    public void save(CollectionManager collectionManager) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Ошибка: Имя файла не указано. Сохранение невозможно.");
            return;
        }

        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file.canWrite()) {
                System.out.println("Ошибка: Нет прав на запись в файл " + fileName);
                return;
            }

            // По заданию сохраняем через java.io.FileOutputStream
            try (FileOutputStream fos = new FileOutputStream(file)) {
                String json = gson.toJson(collectionManager.getCollection());
                fos.write(json.getBytes());
                System.out.println("Коллекция успешно сохранена в файл: " + fileName);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    // Внутренний класс-адаптер для того, чтобы Gson понимал Java 8 LocalDate
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(formatter));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }
}