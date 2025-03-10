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

            // Ввод координат X и Y
            Coordinates coordinates = InsertData.getCoorinates(scanner);

            // Ввод количества участников (больше 0)
            Integer numberOfParticipants = InsertData.getNumberOfParticipants(scanner);

            // Ввод жанра с проверкой
            MusicGenre genre = InsertData.getGenre(scanner);

            // Ввод названия и адреса студии
            Studio studio = InsertData.getStudio(scanner);

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

            // Удаляем внешние кавычки из каждого параметра
            for (int i = 0; i < params.length; i++) {
                // Убираем кавычки
                params[i] = params[i].trim().replaceAll("^\"|\"$", "");
            }

            // Извлечение значений из массива параметров
            String name = params[0];
            long x = Long.parseLong(params[1]);  // Координата X
            long y = Long.parseLong(params[2]);  // Координата Y

            // Обработка пустого значения для количества участников
            int numberOfParticipants = 0;  // Значение по умолчанию
            if (!params[3].isEmpty()) {
                try {
                    numberOfParticipants = Integer.parseInt(params[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Некорректный формат количества участников. Используйте целое число.");
                    return;
                }
            }

            MusicGenre genre = MusicGenre.valueOf(params[4].trim().toUpperCase()); // Жанр
            String studioName = params[5];  // Название студии
            String address = params[6];  // Адрес студии

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