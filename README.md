# Описание программы
Представляет собой консольное Java-приложение для управления коллекцией музыкальных групп.
                                  
### Структура проекта
- Main.java — Главный файл программы, точка входа.
- commands/ — Реализация команд для работы с коллекцией (Clear, Exit, Help и др.).
- commands/interfaces/ — Интерфейсы для команд.
- data/ — Управление коллекцией и обработка команд.
- models/ — Классы моделей данных (MusicBand, MusicGenre, Studio, Coordinates).

### Команды
Программа поддерживает следующие команды:
- help — Вывести список команд.
- info — Вывести информацию о коллекции.
- show — Показать элементы коллекции.
- insert {key} — Добавить элемент в коллекцию.
- update {id} — Обновить элемент по ID.
- remove_key {key} — Удалить элемент по ключу.
- clear — Очистить коллекцию.
- save — Сохранить коллекцию в файл.
- execute_script {file} — Выполнить команды из файла.
- exit — Завершить работу программы.
- filter_by_studio {studio} — Отфильтровать элементы по студии.
- filter_greater_than_genre {genre} — Вывести элементы, у которых жанр больше заданного.
- print_unique_number_of_participants — Вывести уникальное количество участников.
- remove_greater {element} — Удалить элементы, превышающие заданный.
- remove_greater_key {key} — Удалить элементы, ключ которых больше заданного.
- replace_if_lower {key} {element} — Заменить значение по ключу, если новое значение меньше старого.

