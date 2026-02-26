package commands;

import managers.CollectionManager;
import models.MusicBand;

public class Clear extends Command {
    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", 0, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        if (!checkArgAmount(args)) {
            return true;
        }
        collectionManager.clearCollection();
        MusicBand.setNextId(1);
        return true;
    }
}