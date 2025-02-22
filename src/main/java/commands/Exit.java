package commands;

import commands.interfaces.CommandUseable;

/**
 * Команда для завершения работы программы.
 * Выводит сообщение и завершает выполнение приложения.
 */
public class Exit implements CommandUseable {
    /**
     * Выполняет завершение программы.
     * Выводит сообщение перед выходом.
     */
    @Override
    public void execute() {
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Завершить выполнение программы.";
    }
}
