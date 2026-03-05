package commands;

import java.util.Scanner;

import managers.CollectionManager;
import managers.InputManager;

public class FilterContainsName extends Command {
    public FilterContainsName(CollectionManager collectionManager) {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку", 1, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        InputManager inputManager = new InputManager(new Scanner(System.in, System.getProperty("sun.stdout.encoding", "UTF-8")));
        collectionManager.addElement(inputManager.askMusicBand());
        return true;
    }
}