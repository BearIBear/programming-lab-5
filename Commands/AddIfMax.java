package commands;

import managers.ConsoleManager;

import java.util.Scanner;

import managers.CollectionManager;
import managers.ConsoleManager;
import managers.InputManager;
import models.MusicBand;

public class AddIfMax extends Command {
    public AddIfMax(CollectionManager collectionManager, ConsoleManager consoleManager) {
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", 0, collectionManager, consoleManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }

        MusicBand bandToAdd = consoleManager.askMusicBand();
        for (MusicBand band : collectionManager.getCollection()) {
            if (band.compareTo(bandToAdd) > -1) {
                return true;
            }
        }
        collectionManager.addElement(bandToAdd);
        return true;
    }
}