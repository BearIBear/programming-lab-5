package commands;

import managers.ConsoleManager;

import java.util.Scanner;

import managers.CollectionManager;
import managers.ConsoleManager;
import managers.InputManager;

public class Add extends Command {
    public Add(CollectionManager collectionManager, ConsoleManager consoleManager) {
        super("add", "добавить новый элемент в коллекцию", 0, collectionManager, consoleManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        collectionManager.addElement(consoleManager.askMusicBand());
        return true;
    }
}