package commands;

import managers.CollectionManager;
import models.MusicBand;

public class RemoveById extends Command {
    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", 1, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        try {
            long id = Long.parseLong(args[1]);
            if (collectionManager.removeElement(id)) {
                System.out.println("Элемент с id = " + id + " успешно удалён");
                MusicBand.addVacantId(id);
            } else {
                System.out.println("\u001B[31m" + "remove_by_id : Элемент с id = " + id + " не найден" + "\u001B[0m");
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31m" + "remove_by_id : Позиционный параметр id принимает только значения формата long" + "\u001B[0m");
        }
        return true;
    }
}