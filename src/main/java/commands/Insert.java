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

            // Ввод координаты X с проверкой на корректность
            long x = 0;
            boolean xCoordinte = false;
            while (!xCoordinte) {
                System.out.print("Введите координату X: ");
                try {
                    x = scanner.nextLong();
                    if (x > 783) {
                        System.out.println("Ошибка: Координата X не может быть больше 783. Попробуйте снова.");
                    } else {
                        xCoordinte = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: Введено некорректное значение координаты X. Попробуйте снова.");
                    scanner.nextLine();
                }
            }
            // Ввод координаты Y с проверкой на корректность
            long y = 0;
            boolean yCoordinte = false;
            while (!yCoordinte) {
                System.out.print("Введите координату Y: ");
                try {
                    y = scanner.nextLong();
                    yCoordinte = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: Введено некорректное значение координаты Y. Попробуйте снова.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            Coordinates coordinates = new Coordinates(x, y);

            // Ввод количества участников (больше 0)
            Integer numberOfParticipants = null;
            boolean participantsValid = false;
            while (!participantsValid) {
                System.out.print("Введите количество участников (больше 0): ");
                String participantsInput = scanner.nextLine().trim();
                if (participantsInput.isEmpty()) {
                    System.out.println("Ошибка: Количество участников не может быть пустым.");
                } else {
                    try {
                        numberOfParticipants = Integer.parseInt(participantsInput);
                        if (numberOfParticipants > 0) {
                            participantsValid = true;
                        } else {
                            System.out.println("Ошибка: Количество участников должно быть больше 0. Попробуйте снова.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введено некорректное значение (строка). Попробуйте снова.");
                    }
                }
            }

            // Ввод жанра с проверкой
            MusicGenre genre = null;
            while (genre == null) {
                System.out.print("Введите жанр (ROCK, PSYCHEDELIC_ROCK, PSYCHEDELIC_CLOUD_RAP, JAZZ): ");
                if (!scanner.hasNextLine()) {
                    System.out.println("Ошибка: Ожидался ввод жанра.");
                    return;
                }
                try {
                    genre = MusicGenre.valueOf(scanner.nextLine().trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: Неверный жанр. Попробуйте снова.");
                }
            }

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

    public void executeInsertWithParameters(String parameters) {
        try {
            // Разделение строки параметров на части
            String[] params = parameters.split(" ");

            // Проверка на достаточность параметров
            if (params.length != 7) {
                System.out.println("Ошибка: Недостаточно параметров для вставки. Требуется 7 параметров.");
                return;
            }

            // Извлечение значений из массива параметров
            String name = params[0].trim();
            long x = Long.parseLong(params[1].trim());  // Координата X
            long y = Long.parseLong(params[2].trim());  // Координата Y
            int numberOfParticipants = Integer.parseInt(params[3].trim());  // Количество участников
            MusicGenre genre = MusicGenre.valueOf(params[4].trim().toUpperCase());  // Жанр
            String studioName = params[5].trim();  // Название студии
            String address = params[6].trim();  // Адрес студии

            // Создание координат и студии
            Coordinates coordinates = new Coordinates(x, y);
            Studio studio = new Studio(studioName, address);

            // Создание музыкальной группы
            MusicBand musicBand = new MusicBand(name, coordinates, numberOfParticipants, genre, studio);

            // Добавление музыкальной группы в коллекцию
            collectionManager.addMusicBand(musicBand);

            System.out.println("Музыкальная группа успешно добавлена в коллекцию.");

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Некорректный формат числа. Пожалуйста, проверьте координаты и количество участников.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Некорректный жанр. Пожалуйста, используйте один из следующих жанров: ROCK, PSYCHEDELIC_ROCK, PSYCHEDELIC_CLOUD_RAP, JAZZ.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}