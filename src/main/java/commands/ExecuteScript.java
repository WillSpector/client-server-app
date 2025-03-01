package commands;

import commands.interfaces.CommandUseable;
import data.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда для выполнения скрипта из файла.
 * Читает команды из указанного файла и выполняет их последовательно.
 */
public class ExecuteScript implements CommandUseable {
    private final CommandManager commandManager;
    private final Set<String> loadedScripts; ; // Хэш-сет для хранения обработанных файлов

    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.loadedScripts = new HashSet<>();
    }

    /**
     * Выполняет команды из указанного файла.
     * Пользователь вводит имя файла, после чего команды из него считываются и исполняются.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine().trim();

        if (fileName.isEmpty()) {
            System.err.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        if (loadedScripts.contains(fileName)) { // Проверка, был ли файл уже обработан
            System.err.println("Ошибка: файл \"" + fileName + "\" уже был обработан.");
            return;
        }
        // Добавляем файл в список обработанных
        loadedScripts.add(fileName);

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String command = fileScanner.nextLine().trim();
                if (!command.isEmpty()) {
                    System.out.println("> " + command);
                    commandManager.executeCommand(command); // Выполняем команду
                }
            }
            System.out.println("Скрипт выполнен успешно!");
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл \"" + fileName + "\" не найден.");
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }

    public void executeScriptWithParameters(String parameters) {
        String fileName = parameters.trim();

        if (fileName.isEmpty()) {
            System.err.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        // Логика выполнения скрипта с использованием fileName
        System.out.println("Запуск скрипта: " + fileName);
    }

    @Override
    public String getDescription() {
        return "Считать и исполнить скрипт, указав имя файла вручную.";
    }
}