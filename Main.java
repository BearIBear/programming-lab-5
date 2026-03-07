import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jline.reader.Highlighter;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.builtins.Completers.FileNameCompleter;

import commands.*;
import managers.*;

class Main {
    public static void main(String[] args) {
        String fileName = System.getenv("INPUT_FILENAME");
        if (fileName == null || fileName.isBlank()) {
            fileName = "Data.json";
            System.out.println("Переменной окружения INPUT_FILENAME нет");
            System.out.println("Файл по умолчанию: " + fileName);
        }
        
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(fileName);
        fileManager.load(collectionManager);

        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            History history = new DefaultHistory();

            ConsoleManager consoleManager = new ConsoleManager(terminal);
            CommandManager commandManager = new CommandManager();
            commandManager.register(new Help(collectionManager, consoleManager));
            commandManager.register(new Info(collectionManager, consoleManager));
            commandManager.register(new Add(collectionManager, consoleManager));
            commandManager.register(new Show(collectionManager, consoleManager));
            commandManager.register(new Save(collectionManager, consoleManager, fileManager));
            commandManager.register(new Clear(collectionManager, consoleManager));
            commandManager.register(new Exit(collectionManager, consoleManager));
            commandManager.register(new Update(collectionManager, consoleManager));
            commandManager.register(new RemoveById(collectionManager, consoleManager));
            commandManager.register(new Head(collectionManager, consoleManager));
            commandManager.register(new AddIfMax(collectionManager, consoleManager));
            commandManager.register(new CountLessThanDescription(collectionManager, consoleManager));
            commandManager.register(new Script(collectionManager, consoleManager));
            commandManager.register(new FilterContainsName(collectionManager, consoleManager));
            Map<String, Command> commandsList = commandManager.getCommandsList();

            Highlighter consoleHighlighter = new Highlighter() {
                @Override
                public AttributedString highlight(LineReader reader, String buffer) {
                    AttributedStringBuilder builder = new AttributedStringBuilder();
                    if (buffer.length() <= 1) {
                        return builder.append(buffer).toAttributedString();
                    }

                    Set<String> commandNames = commandsList.keySet();
                    String commands = "\\b(";
                    for (int i = 0; i < commandNames.size() - 1; i++) {
                        commands += commandNames.toArray(new String[1])[i] + '|';
                    }
                    commands += commandNames.toArray(new String[1])[commandNames.toArray(new String[1]).length - 1] + ")\\b";
                    Pattern commandsPattern = Pattern.compile(commands, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = commandsPattern.matcher(buffer);

                    if (matcher.find()) {
                        builder.append(buffer.substring(0, matcher.start()));
                        builder.styled(
                                AttributedStyle.BOLD.foreground(AttributedStyle.BLUE),
                                buffer.substring(matcher.start(), matcher.end()));
                        builder.append(buffer.substring(matcher.end())); 
                    } else {
                        builder.append(buffer);
                    }
                    return builder.toAttributedString();
                }
            };
            
            // Если не var, то оно ломается
            var dynamicCompleter = new AggregateCompleter(new StringsCompleter(commandsList.keySet()), new FileNameCompleter());
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(dynamicCompleter)
                    .history(history)
                    .variable(LineReader.HISTORY_FILE, Paths.get("history.txt"))
                    .highlighter(consoleHighlighter)
                    .build();
            consoleManager.setReader(reader);

            boolean state = true;
            while (state) {
                String input = reader.readLine("> ");
                String[] tokens = input.strip().split(" ");
                if (commandsList.containsKey(tokens[0])) {
                    state = commandsList.get(tokens[0].toLowerCase()).run(tokens);
                    consoleManager.getTerminal().flush();
                } else {
                    System.out.println("\u001B[31m" + input + " не распознано как имя команды. Введите help для справки." + "\u001B[0m");
                }
            }
        } catch (IOException e) {
            System.err.println("Не удалось создать терминал: " + e.getMessage());
        }


        // Scanner console = new Scanner(System.in, System.getProperty("sun.stdout.encoding", "UTF-8"));
        // boolean state = true;
        // Map<String, Command> commandsList = commandManager.getCommandsList();
        // while (state) {
        //     System.out.print("> ");
        //     String input = console.nextLine().trim();
        //     String[] tokens = input.split(" ");
        //     if (commandsList.containsKey(tokens[0])) {
        //         state = commandsList.get(tokens[0]).run(tokens);
        //     } else {
        //         System.out.println("\u001B[31m" + input + " не распознано как имя команды. Введите help для справки." + "\u001B[0m");
        //     }
        // }
        // console.close();
    }

//     public static ArrayList<String> removeEmptyStrings(String[] tokens) {
//         ArrayList<String> tokensList = new ArrayList<>();
//         System.out.println(tokensList);
//         for (String string : tokensList) {
//             if (string.isBlank()) {
//                 tokensList.remove(string);
//                 System.out.println("После удаления: " + tokensList);
//             }
//         }
//         System.out.println("После удаления всех: " + tokensList);
//         // String[] outTokens = tokensList.toArray(new String[1]);
//         return tokensList;
//     }
}