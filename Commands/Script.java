package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import managers.CollectionManager;

public class Script extends Command {
    public Script(CollectionManager collectionManager) {
        super("script", "считать и исполнить скрипт из указанного файла", 1, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }

        try {
            File file = new File(args[1]);
            Scanner fileReader = new Scanner(file);
            boolean state = true;
            while (state && fileReader.hasNextLine()) {
                String input = fileReader.nextLine().trim();
                String[] tokens = input.split(" ");
                Map<String, Command> commandsList = commandManager.getCommandsList();

                if (commandManager.checkRecursionExhaustion(file)) {
                    System.out.println("\u001B[31m" + this.name + " : Превышен лимит глубины рекурсии" + "\u001B[0m");
                    fileReader.close();
                    commandManager.clearScriptFile();
                    return true;
                }

                if (commandsList.containsKey(tokens[0])) {
                    commandManager.addScriptFile(file);
                    state = commandsList.get(tokens[0]).run(tokens);
                } else {
                    System.out.println("\u001B[31m" + input + " не распознано как имя команды." + "\u001B[0m");
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31m" + this.name + " : Файл не найден" + "\u001B[0m");
            commandManager.clearScriptFile();
            return true;
        }
        commandManager.clearScriptFile();
        return true;
    }
}