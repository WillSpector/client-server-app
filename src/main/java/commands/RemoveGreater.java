package commands;


import commands.interfaces.CommandUseable;
import data.CollectionManager;
import models.MusicBand;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Команда для удаления всех элементов, превышающих заданный.
 */
public class RemoveGreater extends BaseCommand implements CommandUseable {

    public RemoveGreater(CollectionManager collectionManager) {
        super(collectionManager);
    }


    /**
     * Выполняет команду удаления элементов, ключ которых превышает заданный ID.
     * Пользователь вводит ID, затем из коллекции удаляются все элементы с большим ключом.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите id элемента для сравнения: ");
            int id = scanner.nextInt();

            Iterator<Map.Entry<Integer, MusicBand>> iterator = collectionManager.getCollection().entrySet().iterator();
            int removedCount = 0;
            while (iterator.hasNext()) {
                Map.Entry<Integer, MusicBand> entry = iterator.next();
                if (entry.getKey() > id) {
                    iterator.remove();
                    removedCount++;
                    collectionManager.save();
                }

            }


            System.out.println("Удалено элементов: " + removedCount);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }


    }


    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, превышающие заданный.";
    }
}

