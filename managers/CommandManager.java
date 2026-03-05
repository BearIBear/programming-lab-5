package managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.Command;

public class CommandManager {
    private final Map<String, Command> commandsList = new HashMap<>();
    private ArrayList<File> scriptFiles = new ArrayList<>();
    private int recursionLimit = 5;
    
    public CommandManager() {}

    public void register(Command command) {
        command.setCommandManager(this);
        commandsList.put(command.getName(), command);
    }

    public void listCommands() {
        int max_length = 0;
        int padding = 5;
        for (String name : commandsList.keySet()) {
            max_length = Math.max(max_length, name.length());
        }
        for (String name : commandsList.keySet()) {
            System.out.println(name + " ".repeat(max_length + padding - name.length()) + commandsList.get(name).getDesc());
        }
    }

    public Map<String, Command> getCommandsList() {
        return commandsList;
    }

    public ArrayList<File> getScriptFiles() {
        return scriptFiles;
    }

    public void addScriptFile(File file) {
        scriptFiles.add(file);
    }

    public void clearScriptFile() {
        scriptFiles.clear();
    }

    public boolean checkRecursionExhaustion(File file) {
        int count = 0;
        for (File scriptFile : scriptFiles) {
            if (file.equals(scriptFile)) {
                count += 1;
            }
        }
        if (this.recursionLimit < count) {
            return true;
        }
        return false;
    }

    public int getRecursionLimit() {
        return recursionLimit;
    }
}
