package commands;

import commands.interfaces.CommandUseable;
import data.*;
import models.*;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Команда для добавления нового элемента в коллекцию.
 * Позволяет пользователю добавлять музыкальной группе в коллекцию.
 */
public class Insert extends BaseCommand implements CommandUseable {

    public Insert(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет добавление нового элемента в коллекцию.
     * Запрашивает у пользователя данные о музыкальной группе, координаты, жанр и студию записи и место студии.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Добавление нового элемента в коллекцию:");

            System.out.print("Введите название группы: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод строки.");
                return;
            }
            String name = scanner.nextLine().trim();

            System.out.print("Введите координату X: ");
            long x = scanner.nextLong();

            System.out.print("Введите координату Y: ");
            float y = scanner.nextFloat();
            scanner.nextLine();

            Coordinates coordinates = new Coordinates(x, y);

            System.out.print("Введите количество участников: ");
            String participantsInput = scanner.nextLine().trim();
            Integer numberOfParticipants = participantsInput.isEmpty() ? null : Integer.parseInt(participantsInput);

            System.out.print("Введите жанр (ROCK, PSYCHEDELIC_ROCK, PSYCHEDELIC_CLOUD_RAP, JAZZ): ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод жанра.");
                return;
            }
            MusicGenre genre = MusicGenre.valueOf(scanner.nextLine().trim().toUpperCase());

            System.out.print("Введите название студии: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод названия студии.");
                return;
            }
            String studioName = scanner.nextLine().trim();

            System.out.print("Введите адрес студии: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод адреса студии.");
                return;
            }
            String address = scanner.nextLine().trim();
            Studio studio = new Studio(studioName, address);

            int participants = (numberOfParticipants != null) ? numberOfParticipants : 0;
            MusicBand musicBand = new MusicBand(name, coordinates, participants, genre, studio);
            collectionManager.addMusicBand(musicBand);

            System.out.println("Музыкальная группа успешно добавлена в коллекцию.");

        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Некорректный формат числа.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }


    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию.";
    }
}