package data;

import java.util.Scanner;

/**
 * Предназначен для обработки пользовательского ввода.
 */
public class InputHandler {

    /**
     * Запрашивает у пользователя название файла и добавляет к нему расширение ".json".
     */
    public static String getFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла (без формата): ");
        return scanner.nextLine() + ".json";
    }
}
