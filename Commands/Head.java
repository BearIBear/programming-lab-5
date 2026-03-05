package commands;

import managers.CollectionManager;

public class Head extends Command {
    public Head(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        
        if (collectionManager.getCollection().size() == 0) {
            System.out.println("\u001B[31m" + this.name + " : Коллекция пустая" + "\u001B[0m");
            return true;
        }

        System.out.println(collectionManager.getCollection().peek());

        return true;
    }
}