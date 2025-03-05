package data;

import models.Coordinates;
import models.MusicGenre;
import models.Studio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertData {
    public static Integer getNumberOfParticipants(Scanner scanner) {
        while (true) {
            System.out.print("Введите количество участников (может быть пустым или 0): ");
            String participantsInput = scanner.nextLine().trim();
            if (participantsInput.isEmpty()) {
                return null;
            }
            try {
                int numberOfParticipants = Integer.parseInt(participantsInput);
                if (numberOfParticipants >= 0) {
                    return numberOfParticipants;
                } else {
                    System.out.println("Ошибка: Количество участников не может быть отрицательным. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа. Попробуйте снова.");
            }
        }
    }

    public static Studio getStudio(Scanner scanner) {
        String studioName;
        while (true) {
            System.out.print("Введите название студии: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод названия студии.");
                continue;
            }
            studioName = scanner.nextLine().trim();
            if (!studioName.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: Название студии не может быть пустым. Попробуйте снова.");
        }

        String address;
        while (true) {
            System.out.print("Введите адрес студии: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод адреса студии.");
                continue;
            }
            address = scanner.nextLine().trim();
            if (!address.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: Адрес студии не может быть пустым. Попробуйте снова.");
        }
        return new Studio(studioName, address);
    }

    public static MusicGenre getGenre(Scanner scanner) {
        MusicGenre genre = null;
        while (genre == null) {
            System.out.print("Введите жанр (ROCK, PSYCHEDELIC_ROCK, PSYCHEDELIC_CLOUD_RAP, JAZZ): ");
            if (!scanner.hasNextLine()) {
                System.out.println("Ошибка: Ожидался ввод жанра.");
                return genre;
            }
            try {
                genre = MusicGenre.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Неверный жанр. Попробуйте снова.");
            }
        }
        return genre;
    }

    public static Coordinates getCoorinates(Scanner scanner) {
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

        return coordinates;
    }
}












