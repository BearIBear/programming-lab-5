package commands;

import managers.CollectionManager;
import managers.FileManager;

public class Save extends Command {
    private FileManager fileManager;

    public Save(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл", collectionManager);
        this.fileManager = fileManager; // Этой команде нужен FileManager
    }

    @Override
    public boolean run() {
        fileManager.save(collectionManager);
        return true;
    }
}