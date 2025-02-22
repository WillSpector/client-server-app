package commands;

import commands.interfaces.CommandUseable;
import data.CommandManager;

/**
 * Команда для вывода списка доступных команд.
 * Использует CommandManager для отображения доступных команд и их описаний.
 */
public class Help implements CommandUseable {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Выводит список доступных команд.
     * Использует метод printAvailableCommands() из CommandManager.
     */
    @Override
    public void execute() {
        commandManager.printAvailableCommands();
    }

    @Override
    public String getDescription() {
        return "Вывести список доступных команд.";
    }
}
