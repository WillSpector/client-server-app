package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;
import models.MusicBand;

import java.util.Scanner;

/**
 * Команда для замены количества участников в музыкальной группе, если новое значение меньше текущего.
 */
public class ReplaceIfLower extends BaseCommand implements CommandUseable {
    private final Scanner scanner;

    public ReplaceIfLower(CollectionManager collectionManager) {
        super(collectionManager);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Выполняет команду замены количества участников группы, если новое значение меньше текущего.
     * Пользователь вводит ключ группы и новое количество участников.
     */
    @Override
    public void execute() {
        try {
            System.out.print("Введите ключ (ID группы): ");
            int key = Integer.parseInt(scanner.nextLine().trim());

            if (!collectionManager.containsKey(key)) {
                System.out.println("Ошибка: Элемента с ключом " + key + " не существует.");
                return;
            }

            MusicBand existingBand = collectionManager.getByKey(key);
            if (existingBand == null) {
                System.out.println("Ошибка: Группа с ключом " + key + " отсутствует в коллекции.");
                return;
            }

            System.out.print("Введите новое количество участников: ");
            int newParticipants = Integer.parseInt(scanner.nextLine().trim());

            if ((newParticipants < existingBand.getNumberOfParticipants())) {
                existingBand.setNumberOfParticipants(newParticipants);
                System.out.println("Группа обновлена: новое количество участников = " + newParticipants);
            } else {
                System.out.println("Значение не заменено, так как новое значение не меньше текущего.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Некорректный ввод. Ожидается число.");
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении replace_if_lower: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Заменить количество участников, если оно меньше текущего.";
    }
}
