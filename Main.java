import java.util.Map;
import java.util.Scanner;

import commands.*;
import managers.*;

class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in, System.getProperty("sun.stdout.encoding", "UTF-8"));
        boolean state = true;

        String fileName = System.getenv("INPUT_FILENAME");
        if (fileName == null || fileName.isBlank()) {
            System.out.println("Переменная окружения INPUT_FILENAME нет");
            System.out.println("Файл по умолчанию: Data.json");
            fileName = "Data.json";
        }

        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(fileName);
        fileManager.load(collectionManager);

        CommandManager commandManager = new CommandManager();
        commandManager.register(new Help(collectionManager));
        commandManager.register(new Info(collectionManager));
        commandManager.register(new Add(collectionManager));
        commandManager.register(new Show(collectionManager));
        commandManager.register(new Save(collectionManager, fileManager));
        commandManager.register(new Clear(collectionManager));
        commandManager.register(new Exit(collectionManager));
        commandManager.register(new Update(collectionManager));
        commandManager.register(new RemoveById(collectionManager));

        Map<String, Command> commandsList = commandManager.getCommandsList();

        System.out.print("> ");
        while (state) {
            String input = console.nextLine().trim();
            String[] tokens = input.split(" ");
            if (commandsList.containsKey(tokens[0])) {
                state = commandsList.get(tokens[0]).run(tokens);
            } else {
                System.out.println("\u001B[31m" + input + " не распознано как имя команды. Введите help для справки." + "\u001B[0m");
            }
            System.out.print("> ");
        }
        console.close();
    }
}