package commands;
import managers.CollectionManager;

public abstract class Command {
    private final String name;
    private final String desc;
    protected CollectionManager collectionManager;

    public Command(String name, String desc, CollectionManager collectionManager) {
        this.name = name;
        this.desc = desc;
        this.collectionManager = collectionManager;
    }

    public abstract void run();
    
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}