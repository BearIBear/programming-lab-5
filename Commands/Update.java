package commands;

import managers.CollectionManager;

public class Update extends Command {
    public Update(CollectionManager collectionManager) {
        super("update id", "обновить значение элемента коллекции, id которого равен заданному", collectionManager);
    }

    @Override
    public boolean run() {
        System.out.println("Тип коллекции: PriorityQueue");
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
        System.out.println("Дата инициализации: " + collectionManager.getInitTime());
        return true;
    }
}