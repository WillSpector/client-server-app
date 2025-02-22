package commands;

import commands.interfaces.CommandUseable;
import data.CollectionManager;
import models.MusicBand;

import java.util.*;

/**
 * Команда для вывода списка групп с уникальным количеством участников.
 * Показывает количество участников в музыкальных группах имеют.
 */
public class PrintUniqueNumberOfParticipants extends BaseCommand implements CommandUseable {

    public PrintUniqueNumberOfParticipants(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Выполняет команду вывода уникального количества участников в группах.
     * Если коллекция пуста, выводит соответствующее сообщение.
     */
    @Override
    public void execute() {
        Map<Integer, MusicBand> bands = collectionManager.getCollection();
        if (bands.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        System.out.println("Уникальные количества участников и их группы:");

        for (MusicBand band : bands.values()) {
            Integer participants = band.getNumberOfParticipants();
            if (participants != null && uniqueNumbers.add(participants)) {
                System.out.println("Группа: " + band.getName() + " | Количество участников: " + participants);
            }
        }
    }

    @Override
    public String getDescription() {
        return "вывести список групп с уникальным количеством участников.";
    }
}

