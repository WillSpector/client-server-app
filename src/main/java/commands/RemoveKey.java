package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;

import java.util.Scanner;

/**
 * Команда для удаления элемента из коллекции по заданному ключу.
 */
public class RemoveKey extends BaseCommand implements CommandUseable {

    public RemoveKey(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет команду удаления элемента по заданному ключу.
     * Пользователь вводит ключ, затем удаляется соответствующий элемент, если он найден.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ключ для удаления: ");
        int key = scanner.nextInt();

        if (collectionManager.removeByKey(key)) {
            collectionManager.save();
            System.out.println("Элемент с ключом " + key + " удален.");
        } else {
            System.out.println("Элемент с таким ключом не найден.");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент из коллекции по его ключу.";
    }
}
