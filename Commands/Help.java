package commands;

import managers.CollectionManager;

public class Help extends Command {
    public Help(CollectionManager collectionManager) {
        super("help", "вывести справку по доступным командам", collectionManager);
    }

    @Override
    public boolean run() {
        commandManager.listCommands();
        return true;
    }
}
