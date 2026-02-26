package commands;

import java.util.Scanner;

import managers.CollectionManager;
import managers.InputManager;

public class Add extends Command {
    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", 0, collectionManager);
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