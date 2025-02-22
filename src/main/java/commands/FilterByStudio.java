package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;

import java.util.Scanner;

/**
 * Команда для фильтрации элементов по названию студии.
 * Пользователь вводит название студии, а программа выводит все данные Music Band.
 */
public class FilterByStudio extends BaseCommand implements CommandUseable {

    public FilterByStudio(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет фильтрацию элементов по названию студии.
     * Запрашивает у пользователя название студии и выводит все данные Music Band.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название студии: ");
        String studio = scanner.nextLine().trim();
        System.out.println("Результаты поиска по \"" + studio + "\":");
        collectionManager.filterByStudio(studio);
    }

    @Override
    public String getDescription() {
        return "Вывести элементы, у которых значение поля studio совпадает с заданным.";
    }
}
