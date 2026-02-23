import java.nio.channels.MulticastChannel;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.PriorityQueue;

import javax.tools.DocumentationTool.Location;

import commands.*;
import managers.*;
import models.*;

import com.google.gson.Gson;

class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        // 1. Инициализируем менеджеры
        CollectionManager collectionManager = new CollectionManager();
        // На будущее: FileManager fileManager = new FileManager(System.getenv("INPUT_FILENAME"));
        // fileManager.load(collectionManager);

        // 2. Инициализируем команды
        Map<String, Command> commandsList = new HashMap<>();
        commandsList.put("help", new Help(collectionManager));
        commandsList.put("info", new Info(collectionManager));
        commandsList.put("add", new Add(collectionManager));
        commandsList.put("show", new Show(collectionManager));

        // 3. Главный цикл (твой обработчик команд)
        System.out.print("> ");
        while (console.hasNextLine()) {
            String input = console.nextLine().trim();
            String[] tokens = input.split(" "); // Разделяем команду и её аргументы
            String commandName = tokens[0];

            if (commandsList.containsKey(commandName)) {
                // В идеале в run() нужно будет передавать аргументы tokens
                commandsList.get(commandName).run(); 
            } else if (commandName.equals("exit")) {
                break;
            } else {
                System.out.println(commandName + " не является командой. Введите help.");
            }
            System.out.print("> ");
        }
    }
}