package data;

import commands.*;
import commands.interfaces.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Отвечает за управление командами, их регистрацию и выполнение.
 */
public class CommandManager implements CommandRegistry {
    private final Map<String, CommandUseable> commands = new TreeMap<>();
    private static final String space = "\\s+";
    private static final int element = 0;
    private static final int splitLimit = 2;

    public CommandManager(String fileName, CollectionManager collectionManager) {
        initializeCommands(fileName, collectionManager);
    }

    /**
     * Регистрирует команды
     */
    public void initializeCommands(String fileName, CollectionManager collectionManager) {
        commands.put("help", new Help(this));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("insert", new Insert(collectionManager));
        commands.put("update", new Update(collectionManager));
        commands.put("remove_key", new RemoveKey(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("save", new Save(fileName, collectionManager));
        commands.put("execute_script", new ExecuteScript(this));
        commands.put("exit", new Exit());
        commands.put("remove_greater", new RemoveGreater(collectionManager));
        commands.put("replace_if_lower", new ReplaceIfLower(collectionManager));
        commands.put("remove_greater_key", new RemoveGreaterKey(collectionManager));
        commands.put("filter_by_studio", new FilterByStudio(collectionManager));
        commands.put("filter_greater_than_genre", new FilterGreaterThanGenre(collectionManager));
        commands.put("print_unique_number_of_participants", new PrintUniqueNumberOfParticipants(collectionManager));
    }

    /**
     * Возвращает название команды по ее порядковому номеру
     */

    private String getCommandByNumber(int number) {
        int index = 1;
        for (String command : commands.keySet()) {
            if (index == number) {
                return command;
            }
            index++;
        }
        return null;
    }

    /**
     * Выполняет команду по ее названию или порядковому номеру
     */
    @Override
    public void executeCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Ошибка: Пустая команда не может быть выполнена.");
            return;
        }

        // Проверяем, ввёл ли пользователь число (номер команды)
        try {
            int commandNumber = Integer.parseInt(input.trim());
            String commandName = getCommandByNumber(commandNumber);

            if (commandName != null) {
                commands.get(commandName).execute();  // Просто вызываем execute без параметров
            } else {
                System.out.println("Ошибка: Команда с номером " + commandNumber + " не найдена.");
            }
            return;
        } catch (NumberFormatException ignored) {
        }

        String commandName = parseCommand(input);  // Извлекаем команду
        CommandUseable commandUseable = commands.get(commandName);

        if (commandUseable == null) {
            System.out.println("Ошибка: Команда '" + commandName + "' не найдена.");
            return;
        }
        // Извлекаем параметры (оставшуюся часть после команды)
        String parameters = input.substring(commandName.length()).trim();

        // Прямой вызов соответствующего метода для обработки параметров
        switch (commandUseable) {
            case ExecuteScript executeScript ->
                // В случае с ExecuteScript параметры будут переданы в executeScriptWithParameters
                    executeScript.executeScriptWithParameters(parameters);
            case Insert insert -> {
                // Прямой вызов executeInsertWithParameters только при передаче параметров через ExecuteScript
                if (!parameters.isEmpty()) {
                    ((Insert) commandUseable).executeInsertWithParameters(parameters);
                } else {
                    // Для ручного ввода вызываем обычный execute() метод
                    commandUseable.execute();
                }
            }

            case Update update -> update.execute();

            default ->
                // Для всех остальных команд без параметров
                    commandUseable.execute();
        }
    }

    /**
     * Извлекает название команды из введенного пользователем текста.
     */
    private String parseCommand(String input) {
        return input.trim().split(space, splitLimit)[element];
    }

    /**
     * Выводит список доступных команд с их порядковыми номерами и описаниями.
     */
    @Override
    public void printAvailableCommands() {
        System.out.println("Список доступных команд:");
        int index = 1;
        for (Map.Entry<String, CommandUseable> entry : commands.entrySet()) {
            System.out.println(index + ". " + entry.getKey() + " - " + entry.getValue().getDescription());
            index++;
        }
    }
}