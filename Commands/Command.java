package commands;
import managers.CollectionManager;
import managers.CommandManager;

public abstract class Command {
    private final String name;
    private final String desc;
    protected CommandManager commandManager;
    protected CollectionManager collectionManager;

    public Command(String name, String desc, CollectionManager collectionManager) {
        this.name = name;
        this.desc = desc;
        this.collectionManager = collectionManager;
    }

    public abstract boolean run();
    
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    
    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}