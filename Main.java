import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.*;
import managers.*;

class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String fileName = System.getenv("INPUT_FILENAME");
        if (fileName == null || fileName.isBlank()) {
            System.out.println("Переменная окружения INPUT_FILENAME нет");
            System.out.println("Файл по умолчанию: Data.json");
            fileName = "Data.json";
        }

        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(fileName);
        fileManager.load(collectionManager);

        Map<String, Command> commandsList = new HashMap<>();
        commandsList.put("help", new Help(collectionManager));
        commandsList.put("info", new Info(collectionManager));
        commandsList.put("add", new Add(collectionManager));
        commandsList.put("show", new Show(collectionManager));
        commandsList.put("save", new Save(collectionManager, fileManager));

        System.out.print("> ");
        while (true) {
            String input = console.nextLine().trim();
            String[] tokens = input.split(" ");
            String commandName = tokens[0];

            if (commandsList.containsKey(commandName)) {
                commandsList.get(commandName).run(); 
            } else if (commandName.equals("exit")) {
                break;
            } else {
                System.out.println(commandName + " не является командой. Введите help.");
            }
            System.out.print("> ");
        }
        console.close();
    }
}