package commands;

import commands.interfaces.CommandUseable;
import data.InsertData;
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

            // Ввод координат X и Y
            Coordinates coordinates = InsertData.getCoorinates(scanner);

            // Ввод количества участников (больше 0)
            Integer numberOfParticipants = InsertData.getNumberOfParticipants(scanner);

            // Ввод жанра с проверкой
            MusicGenre genre = InsertData.getGenre(scanner);

            // Ввод названия и адреса студии
            Studio studio = InsertData.getStudio(scanner);

            // Получаем старый объект
            MusicBand oldBand = collectionManager.getByKey(id);
            if (oldBand == null) {
                System.out.println("Группа с таким ID не найдена.");
                return;
            }
            // Создание и обновление объекта в коллекции
            MusicBand updatedBand = new MusicBand(name, coordinates, numberOfParticipants, genre, studio);
            updatedBand.setId(oldBand.getId()); // Устанавливаем старый ID
            collectionManager.getCollection().put(id, updatedBand);
            collectionManager.save();
            System.out.println("Элемент с id " + id + " успешно обновлён.");

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
    /**
     * Метод для обработки обновлений с параметрами из строки (например, для скриптов).
     * Параметры передаются как строка: id,name,x,y,genre,studioName,studioAddress
     */
    public void executeUpdateWithParameters(String parameters) {
        try {
            String[] params = parameters.split(" ");
            if (params.length != 8) {
                System.out.println("Ошибка: Недостаточно параметров для обновления. Требуется 8 параметров.");
                return;
            }

            long id = Long.parseLong(params[0].trim());
            MusicBand oldMusicBand = collectionManager.getByKey((int) id);
            if (oldMusicBand == null) {
                System.out.println("Группа с таким ID не найдена.");
                return;
            }

            String name = params[1].trim();
            long x = Long.parseLong(params[2].trim());
            long y = Long.parseLong(params[3].trim());

            // Обработка пустого значения для количества участников
            Integer numberOfParticipants = null;
            if (!params[4].trim().isEmpty()) {
                numberOfParticipants = Integer.parseInt(params[4].trim());
            }

            MusicGenre genre = MusicGenre.valueOf(params[5].trim().toUpperCase());
            String studioName = params[6].trim();
            String studioAddress = params[7].trim();

            Coordinates coordinates = new Coordinates(x, y);
            Studio studio = new Studio(studioName, studioAddress);

            MusicBand updatedBand = new MusicBand(name, coordinates, numberOfParticipants, genre, studio);
            updatedBand.setId(oldMusicBand.getId()); // Сохраняем старый ID
            collectionManager.getCollection().put((int) id, updatedBand);
            collectionManager.save();
            System.out.println("Элемент с id " + id + " успешно обновлён.");

        } catch (Exception e) {
            System.out.println("Ошибка при обработке параметров: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному.";
    }
}
