package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;

import java.util.Scanner;

/**
 * Команда для фильтрации элементов, у которых жанр музыки превышает заданное значение.
 * Пользователь вводит название жанра, а программа выводит жанры, которых больше по количеству.
 */
public class FilterGreaterThanGenre extends BaseCommand implements CommandUseable {

    public FilterGreaterThanGenre(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет фильтрацию элементов по жанру.
     * Запрашивает у пользователя название жанра и выводит жанры, которых больше по количеству.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите жанр музыки (выведутся группы с жанром больше заданного): ");
        String genre = scanner.nextLine().trim();
        System.out.println("Результаты фильтрации по жанру, превышающему \"" + genre + "\":");
        collectionManager.filterGreaterThanGenre(genre);
    }

    @Override
    public String getDescription() {
        return "Вывести элементы, у которых значение поля genre больше заданного.";
    }
}
