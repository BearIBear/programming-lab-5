package commands;

import java.util.PriorityQueue;
import java.util.Scanner;

import managers.CollectionManager;
import managers.InputManager;
import models.MusicBand;

public class Update extends Command {
    public Update(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        int id = Integer.parseInt(args[1]); 
        PriorityQueue<MusicBand> musicBands = collectionManager.getCollection();
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                collectionManager.removeElement(id);
                MusicBand bandToInsert = new InputManager(new Scanner(System.in, System.getProperty("sun.stdout.encoding", "UTF-8"))).askMusicBand();
                bandToInsert.setId(id);
                collectionManager.addElement(bandToInsert);
                return true;
            }
        }
        System.out.println("\u001B[31m" + "update : Элемент с id = " + id + " не найден" + "\u001B[0m");
        return true;
    }
}