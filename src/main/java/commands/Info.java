package commands;


import commands.interfaces.CommandUseable;
import data.CollectionManager;


/**
 * Команда для вывода информации о коллекции.
 * Показывает тип коллекции, дату инициализации и количество элементов.
 */
public class Info extends BaseCommand implements CommandUseable {

    public Info(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выводит информацию о коллекции.
     * Отображает тип коллекции, дату инициализации и количество элементов.
     */
    @Override
    public void execute() {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getName());
        System.out.println("Дата инициализации: " + collectionManager.getInitializationDate());
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
    }

    @Override
    public String getDescription() {
        return "Вывести информацию о коллекции (тип, дата инициализации, количество элементов).";
    }
}
