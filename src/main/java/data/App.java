package data;

/**
 * Класс App отвечает за инициализацию всех ключевых компонентов и запуск основного цикла обработки команд.
 */
public class App {

    /**
     * Запускает приложение, инициализируя менеджеры команд и коллекций, а затем передает управление
     */
    public void start() {
        String fileName = InputHandler.getFileName();

        CollectionManager collectionManager = new CollectionManager(fileName);
        collectionManager.loadFromFile();

        CommandManager commandManager = new CommandManager(fileName, collectionManager);

        ConsoleHandler consoleHandler = new ConsoleHandler(commandManager);
        consoleHandler.start();
    }
}
