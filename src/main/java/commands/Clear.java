package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;

/**
 * Команда для очистки коллекции.
 * Удаляет все элементы коллекции и сохраняет изменения.
 */
public class Clear extends BaseCommand implements CommandUseable {

    public Clear(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет очистку коллекции.
     * Удаляет все элементы и сохраняет изменения.
     */
    @Override
    public void execute() {
        collectionManager.clear();
        collectionManager.save();
        System.out.println("Коллекция успешно очищена!");
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию.";
    }
}
