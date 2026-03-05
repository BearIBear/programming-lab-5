package commands;

import java.util.Scanner;

import managers.CollectionManager;
import managers.InputManager;
import models.MusicBand;

public class AddIfMax extends Command {
    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }

        InputManager inputManager = new InputManager(new Scanner(System.in, System.getProperty("sun.stdout.encoding", "UTF-8")));
        MusicBand bandToAdd = inputManager.askMusicBand();
        for (MusicBand band : collectionManager.getCollection()) {
            if (band.compareTo(bandToAdd) > -1) {
                return true;
            }
        }
        collectionManager.addElement(bandToAdd);
        return true;
    }
}