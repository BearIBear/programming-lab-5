package commands;

import managers.CollectionManager;

public class Exit extends Command {
    public Exit(CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)", collectionManager);
    }

    @Override
    public boolean run() {
        System.exit(0);
        return false;
    }
}