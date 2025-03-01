package commands;

import commands.interfaces.CommandUseable;
import data.*;
import models.*;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Команда для обновления значения элемента коллекции по заданному ключу.
 */
public class Update extends BaseCommand implements CommandUseable {

    public Update(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет команду обновления элемента коллекции по ключу.
     * Пользователь вводит ключ и новые данные, после чего элемент обновляется.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите id элемента для обновления: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // очищаем буфер после числа

            MusicBand oldMusicBand = collectionManager.getCollection().get(id);

            if (oldMusicBand == null) {
                System.out.println("Элемент с id " + id + " не найден.");
                return;
            }

            // Сбор данных для нового объекта MusicBand
            System.out.print("Введите название группы: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите координату X: ");
            long x = scanner.nextLong();

            System.out.print("Введите координату Y: ");
            float y = scanner.nextFloat();
            scanner.nextLine();// очищаем буфер после числа

            Coordinates coordinates = new Coordinates(x, y);

            System.out.print("Введите количество участников (или оставьте пустым): ");
            String participantsInput = scanner.nextLine().trim();
            Integer numberOfParticipants = participantsInput.isEmpty() ? null : Integer.parseInt(participantsInput);

            System.out.print("Введите жанр (ROCK, PSYCHEDELIC_ROCK, PSYCHEDELIC_CLOUD_RAP, JAZZ): ");
            MusicGenre genre = MusicGenre.valueOf(scanner.nextLine().trim().toUpperCase());

            System.out.print("Введите название студии: ");
            String studioName = scanner.nextLine().trim();
            System.out.print("Введите адрес студии: ");
            String address = scanner.nextLine().trim();
            Studio studio = new Studio(studioName, address);

            // Создание и обновление объекта в коллекции
            MusicBand updatedBand = new MusicBand(name, coordinates, numberOfParticipants, genre, studio);

            collectionManager.getCollection().put(id, updatedBand);
            collectionManager.save();
            System.out.println("Элемент с id " + id + " успешно обновлён.");

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному.";
    }
}
