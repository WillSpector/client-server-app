package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;

/**
 * Команда для вывода всех элементов коллекции.
 */
public class Show extends BaseCommand implements CommandUseable {

    public Show(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет команду, выводя все элементы коллекции в консоль.
     * Если коллекция пуста, выводится соответствующее сообщение.
     */
    @Override
    public void execute() {
        collectionManager.printCollection();
    }

    @Override
    public String getDescription() {
        return "Вывести все элементы коллекции.";
    }
}
