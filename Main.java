import java.util.Map;
import java.util.Scanner;

import commands.*;
import managers.*;

class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
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
        commandManager.register("help", new Help(collectionManager));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("add", new Add(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("save", new Save(collectionManager, fileManager));
        commandManager.register("clear", new Clear(collectionManager));
        commandManager.register("exit", new Exit(collectionManager));
        commandManager.register("update", new Update(collectionManager));
        Map<String, Command> commandsList = commandManager.getCommandsList();

        System.out.print("> ");
        while (state) {
            String input = console.nextLine().trim();
            String[] tokens = input.split(" ");
            if (commandsList.containsKey(tokens[0])) {
                state = commandsList.get(tokens[0]).run();
            } else {
                System.out.println(input + " не является командой. Введите help.");
            }
            System.out.print("> ");
        }
        console.close();
    }
}