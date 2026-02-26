package commands;

import managers.CollectionManager;

public class Info extends Command {
    public Info(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        System.out.println("Тип коллекции: PriorityQueue");
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
        System.out.println("Дата инициализации: " + collectionManager.getInitTime());
        return true;
    }
}