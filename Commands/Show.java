package commands;

import managers.CollectionManager;
import models.MusicBand;

public class Show extends Command {
    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        var collection = collectionManager.getCollection();
        for (MusicBand musicBand : collection) {
            System.out.println("ID группы: " + musicBand.getId());
            System.out.println("Название группы: " + musicBand.getName());
            System.out.println("Описание группы: " + musicBand.getDescription());
            System.out.println("Фронтман: " + musicBand.getFrontMan());
            System.out.println("Жанр: " + musicBand.getGenre());
            System.out.println("Дата создания: " + musicBand.getCreationDate());
            System.out.println("Количество участников: " + musicBand.getNumberOfParticipants());
            System.out.println("Количество синглов: " + musicBand.getSinglesCount());
            System.out.println("Координаты: " + musicBand.getCoordinates());
        }
        return true;
    }
}