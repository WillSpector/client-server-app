package data;

import java.util.Scanner;

/**
 * Отвечает за обработку ввода команд из консоли.
 * Позволяет пользователю вводить команды и управляет их выполнением.
 */


public class ConsoleHandler {
    private final CommandManager commandManager;

    public ConsoleHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Запускает обработку пользовательского ввода из консоли.
     * Для выхода из программы необходимо ввести команду "exit".
     */

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите команду: ");
                String command = scanner.nextLine().trim();
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Завершение работы программы...");
                    break;
                }
                if (!command.isEmpty()) {
                    commandManager.executeCommand(command);
                } else {
                    System.out.println("Ошибка: пустая команда");
                }
            }
        }
    }
}
