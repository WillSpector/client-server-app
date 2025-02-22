package commands;


import commands.interfaces.CommandUseable;
import data.CollectionManager;
import models.MusicBand;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Команда для удаления всех элементов, ключ которых превышает заданный.
 */
public class RemoveGreaterKey extends BaseCommand implements CommandUseable {

    public RemoveGreaterKey(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет команду удаления элементов, ключ которых превышает заданный Ключ.
     * Пользователь вводит ключ, затем из коллекции удаляются все элементы с большим ключом.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите ключ (id), выше которого следует удалить элементы: ");
            int keyThreshold = scanner.nextInt();

            Iterator<Map.Entry<Integer, MusicBand>> iterator = collectionManager.getCollection().entrySet().iterator();

            int removedCount = 0;
            while (iterator.hasNext()) {
                Map.Entry<Integer, ?> entry = iterator.next();
                if (entry.getKey() > keyThreshold) {
                    iterator.remove();
                    removedCount++;
                    collectionManager.save();
                }
            }

            System.out.println("Удалено элементов: " + removedCount);


        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, ключ которых превышает заданный.";
    }
}
