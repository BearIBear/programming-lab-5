package managers;

import java.util.HashMap;
import java.util.Map;

import commands.Command;

public class CommandManager {
    private final Map<String, Command> commandsList = new HashMap<>();
    
    public CommandManager() {}

    public void register(String name, Command command) {
        command.setCommandManager(this);
        commandsList.put(name, command);
    }

    public void listCommands() {
        for (String name : commandsList.keySet()) {
            System.out.println(name + " ".repeat(10 - name.length()) + commandsList.get(name).getDesc());
        }
    }

    public Map<String, Command> getCommandsList() {
        return commandsList;
    }
}
