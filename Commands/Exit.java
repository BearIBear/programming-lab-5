package commands;

import managers.CollectionManager;

public class Exit extends Command {
    public Exit(CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        System.exit(0);
        return false;
    }
}