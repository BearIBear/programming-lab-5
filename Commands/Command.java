package commands;
import managers.CollectionManager;
import managers.CommandManager;

public abstract class Command {
    private final String name;
    private final String desc;
    private final int argsAmount;
    protected CommandManager commandManager;
    protected CollectionManager collectionManager;

    public Command(String name, String desc, int argsAmount, CollectionManager collectionManager) {
        this.name = name;
        this.desc = desc;
        this.argsAmount = argsAmount + 1;
        this.collectionManager = collectionManager;
    }

    public abstract boolean run(String[] args);
    
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

    public boolean checkArgAmount(String[] args) {
        if (args.length == this.argsAmount) {
            System.out.println("\u001B[31m" + this.name + " : Недостаточно параметров" + "\u001B[0m");
            return false;
        }
        if (args.length > this.argsAmount) {
            System.out.println("\u001B[31m" + this.name + " : Слишком много параметров" + "\u001B[0m");
            return false;
        }
        return true;
    }
}