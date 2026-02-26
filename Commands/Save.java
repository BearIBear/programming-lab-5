package commands;

import managers.CollectionManager;
import managers.FileManager;

public class Save extends Command {
    private FileManager fileManager;

    public Save(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл", 0, collectionManager);
        this.fileManager = fileManager;
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        fileManager.save(collectionManager);
        return true;
    }
}