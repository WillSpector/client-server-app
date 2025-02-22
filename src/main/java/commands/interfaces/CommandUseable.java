package commands.interfaces;

/**
 * Интерфейс, представляющий исполняемую команду.
 * Определяет методы для выполнения команды и получения её описания.
 */
public interface CommandUseable {

    /**
     * Выполняет команду.
     */
    void execute();

    /**
     * Возвращает описание команды.
     */
    String getDescription();
}
