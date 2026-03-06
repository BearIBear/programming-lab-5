package commands;

import java.util.Arrays;

import managers.CollectionManager;
import models.MusicBand;

public class FilterContainsName extends Command {
    public FilterContainsName(CollectionManager collectionManager) {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку", 1, collectionManager);
    }

    @Override
    public boolean run(String[] args) {
        String description = String.join("", Arrays.copyOfRange(args, 1, args.length));
        String [] newArgs = {args[0], description};
        if (!checkArgAmount(newArgs)) {
            return true;
        }

        for (MusicBand band : collectionManager.getCollection()) {
            if (band.getName().equals(newArgs[1])) {
                System.out.println(band);
            }
        }
        return true;
    }
}