import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  // Import the Scanner class

import Commands.Command;
import Commands.Help;

class Main {
  public static void main(String[] args) {
    Command command;
    Map<String, Command> commandsList = new HashMap<>();
    commandsList.put("help", new Help());

    Scanner console = new Scanner(System.in);
    System.out.print("> ");
    while (true) {
        String input = console.nextLine();
        if (commandsList.containsKey(input)) {
            command = commandsList.get(input);
            break;
        }
        System.out.println(input + " не является командой");
        System.out.print("> ");
    }
    command.run();
    
    console.close();
  }
}