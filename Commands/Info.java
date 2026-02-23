package commands;

import managers.CollectionManager;

public class Info extends Command {
    public Info(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции", collectionManager);
    }

    @Override
    public void run() {
        System.out.println("Тип коллекции: PriorityQueue");
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
        System.out.println("Дата инициализации: " + collectionManager.getInitTime());
    }
}