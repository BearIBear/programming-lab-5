package commands;

import java.util.Scanner;

import managers.CollectionManager;
import managers.InputManager;

public class Add extends Command {
    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", collectionManager);
    }

    @Override
    public void run() {
        Scanner console = new Scanner(System.in);
        InputManager inputManager = new InputManager(console);
        collectionManager.addElement(inputManager.askMusicBand());
    }
}