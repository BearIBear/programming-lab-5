package commands;

import managers.CollectionManager;

public class Clear extends Command {
    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", collectionManager);
    }

    @Override
    public boolean run() {
        collectionManager.clearCollection();
        return true;
    }
}