package commands.interfaces;


/**
 * Интерфейс для управления регистрацией и выполнением команд.
 */
public interface CommandRegistry {

    /**
     * Выполняет команду, переданную в виде строки ввода.
     */
    void executeCommand(String input);

    /**
     * Выводит список доступных команд.
     */
    void printAvailableCommands();
}
