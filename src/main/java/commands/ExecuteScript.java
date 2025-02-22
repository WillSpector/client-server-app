package commands;

import commands.interfaces.CommandUseable;
import data.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из файла.
 * Читает команды из указанного файла и выполняет их последовательно.
 */
public class ExecuteScript implements CommandUseable {
    private final CommandManager commandManager;

    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
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

    @Override
    public String getDescription() {
        return "Считать и исполнить скрипт, указав имя файла вручную.";
    }
}
