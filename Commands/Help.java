package commands;

import managers.CollectionManager;

public class Help extends Command {
    public Help(CollectionManager collectionManager) {
        super("help", "вывести справку по доступным командам", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        commandManager.listCommands();
        return true;
    }
}
