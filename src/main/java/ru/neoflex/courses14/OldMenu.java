package ru.neoflex.courses14;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;
import ru.neoflex.courses14.services.LocationOfAirplanesService;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OldMenu {
    private static final Logger log = LogManager.getLogger(OldMenu.class);
    private int from;
    private Airport rememberedAirport;
    private Airplane rememberedAirplane;

    public void exit() {
        log.debug("Menu: выход");
        System.exit(0);
    }

    public void incorrectCommand() {
        log.debug("Menu: неверная команда");
        System.out.println("Ошибка: Введена неверная команда.");
    }

    public void begin() {
        log.debug("Menu: начало");
        System.out.println("Начало");
        System.out.println("Самолеты: 1");
        System.out.println("Аропорты: 2");
        System.out.println("Выход: 0");
        int command = getCommandInt();
        switch (command) {
            case (1):
                airplanes();
                break;
            case (2):
                airports();
                break;
            case (0):
                exit();
                break;
            default:
                incorrectCommand();
                break;
        }

    }

    public void airplanes() {
        while (true) {
            log.debug("Menu: самолеты");
            from = 1;
            System.out.println("Самолеты");
            System.out.println("Создать: 1");
            System.out.println("Искать: 2");
            System.out.println("Список всех: 3");
            System.out.println("Назад: 0");
            int command = getCommandInt();
            switch (command) {
                case (1):
                    create(1);
                    break;
                case (2):
                    find(1);
                    break;
                case (3):
                    getAll(1);
                    break;
                case (0):
                    begin();
                    break;
                default:
                    incorrectCommand();
                    break;
            }
        }
    }

    public void airports() {
        while (true) {
            log.debug("Menu: аэропорты");
            System.out.println("Аэропорты");
            System.out.println("Создать: 1");
            System.out.println("Искать: 2");
            System.out.println("Список всех: 3");
            System.out.println("Назад: 0");
            int command = getCommandInt();
            switch (command) {
                case (1):
                    create(2);
                    break;
                case (2):
                    find(2);
                    break;
                case (3):
                    getAll(2);
                    break;
                case (0):
                    begin();
                    break;
                default:
                    incorrectCommand();
                    break;
            }
        }
    }

    public void create(int type) {
        log.debug("Menu: создать");
        switch (type) {
            case (1): {
                System.out.println("Создать самолет");
                try{
                new AirplanesService().add((Airplane) inputFields(type, new Random().nextLong()));
                } catch (EntityNotFoundException e) {
                    log.error("Ошибка при добавлении Airplane в хранилище");
                    System.out.println("Не удалось создать самолет");
                    break;
                }
                System.out.println("Самолет создан");
                break;
            }
            case (2): {
                System.out.println("Создать аэропорт");
                try {
                    new AirportsService().add((Airport) inputFields(type, new Random().nextLong()));
                } catch (EntityNotFoundException e) {
                    log.error("Ошибка при добавлении Airport в хранилище");
                    System.out.println("Не удалось создать аэропорт");
                    break;
                }
                System.out.println("Аэропорт создан");
            }
        }
    }

    public Object inputFields(int type, Long id) {
        Object object = null;
        switch (type) {
            case (1):
                System.out.println("Введите бортовой номер");
                String serialNumber = getCommandString();
                System.out.println("Введите модель");
                String model = getCommandString();
                System.out.println("Введите место назначения");
                String destination = getCommandString();
                System.out.println("Введите дату выпуска");
                String releaseDate = getCommandString();
                System.out.println("Введите компания");
                String operator = getCommandString();
                object = new Airplane(id, serialNumber, model, destination, releaseDate, operator);
                break;
            case (2):
                System.out.println("Введите город");
                String city = getCommandString();
                System.out.println("Введите код IATA");
                String codeIATA = getCommandString();
                System.out.println("Введите пропускную способность");
                String throughput = getCommandString();
                object = new Airport(id, city, codeIATA, throughput);
        }
        return object;
    }

    public void find(int type) {
        log.debug("Menu: Искать");
        System.out.println("Искать");
        System.out.println("Искать по ID: 1");
        switch (type) {
            case (1): {
                System.out.println("Искать по месту назначения: 2");
                int command = getCommandInt();
                switch (command) {
                    case (1):
                        findById(1);
                        break;
                    case (2):
                        findByDestination();
                        break;
                    default:
                        incorrectCommand();
                        break;
                }
                break;
            }
            case (2): {
                System.out.println("Искать по городу: 2");
                int command = getCommandInt();
                switch (command) {
                    case (1):
                        findById(2);
                        break;
                    case (2):
                        findByCity();
                        break;
                    default:
                        incorrectCommand();
                        break;
                }
            }
        }
    }

    public void findByDestination() {
        log.debug("Menu: поиск по месту назначения");
        System.out.println("Поиск по месту назначения");
        System.out.println("Введите место назначения");
        String destination = getCommandString();
        try {
            searchResultsForFind(1, new AirplanesService().getByDestination(destination));
        } catch (EntityNotFoundException e) {
            System.out.println("Результатов не найдено");
            log.error("не найдено Airplane c destination =" + destination, e);
        }
    }

    public void findByCity() {
        log.debug("Menu: поиск по городу");
        System.out.println("Поиск по городу");
        System.out.println("Введите город");
        String city = getCommandString();
        try {
            searchResultsForFind(2, new AirportsService().getByCity(city));
        } catch (EntityNotFoundException e) {
            log.error("не найдено Airport c city = " + city, e);
            System.out.println("Результатов не найдено");
        }
    }

    public void findById(int type) {
        log.debug("Menu: поиск по ID");
        System.out.println("Поиск по ID");
        System.out.println("Введите ID");
        Long id = getCommandLong();
        switch (type) {
            case (1):
                try {
                    Airplane airplane = new AirplanesService().getById(id);
                    searchResult(airplane);
                } catch (EntityNotFoundException e) {
                    log.error("не найдено Airplane c id = " + id, e);
                    System.out.println("Самолетов с таким ID не найдено");
                }
                break;
            case (2):
                try {
                    Airport airport = new AirportsService().getById(id);
                    searchResult(airport);
                } catch (EntityNotFoundException e) {
                    log.error("не найдено Airport c id = " + id, e);
                    System.out.println("Аэропортов с таким id не найдено");
                }
        }
    }

    public void searchResultsForFind(int type, List list) {
        log.debug("Menu: результаты поиска");
        System.out.println("Результаты поиска");
        printResult(type, list);
        find(type);
    }

    public void searchResultsForAirport(int type, List list) {
        System.out.println("Результаты поиска");
        printResult(type, list);
        searchResult(rememberedAirport);
    }

    public void printResult(int type, List list) {
        if (list.size() == 0) {
            System.out.println("Результатов не найдено");
        } else {
            int i = 0;
            switch (type) {
                case (1):
                    String formatAirplane = "%-15s %-20s %-20s %-15s %-20s %-20s%n";
                    System.out.printf(formatAirplane, "Бортовой номер", "Модель", "Место назначения", "Дата выпуска", "Компания", "ID");
                    for (Object object : list) {
                        i++;
                        Airplane airplane = (Airplane) object;
                        System.out.printf(formatAirplane, i + ". " + airplane.getSerialNumber(), airplane.getModel(), airplane.getDestination(), airplane.getReleaseDate(), airplane.getOperator(), airplane.getAirplaneId());
                    }
                    break;
                case (2):
                    String formatAirport = "%-15s %-10s %-25s %-20s%n";
                    System.out.printf(formatAirport, "Город", "Код IATA", "Пропускная способность", "ID");
                    for (Object object : list) {
                        i++;
                        Airport airport = (Airport) object;
                        System.out.printf(formatAirport, i + ". " + airport.getCity(), airport.getCodeIATA(), airport.getThroughput(), airport.getAirportId());
                    }
            }
        }
    }

    public void searchResult(Airplane airplane) {
        log.debug("Menu: самолет");
        System.out.println("Самолет");
        System.out.println("Бортовой номер: " + airplane.getSerialNumber());
        System.out.println("Модель: " + airplane.getModel());
        System.out.println("Место назначения: " + airplane.getDestination());
        System.out.println("Дата выпуска: " + airplane.getReleaseDate());
        System.out.println("Компания: " + airplane.getOperator());
        System.out.println("Действия с самолетом");
        int command;
        switch (from) {
            case (1):
                System.out.println("Удалить: 1");
                System.out.println("Изменить: 2");
                System.out.println("Назад: 0");
                command = getCommandInt();
                switch (command) {
                    case (1):
                        delete(1, airplane.getAirplaneId());
                        break;
                    case (2):
                        update(1, airplane.getAirplaneId());
                        break;
                    case (0):
                        airplanes();
                        break;
                    default:
                        incorrectCommand();
                        break;
                }
            case (2):
                System.out.println("Добавить: 1");
                System.out.println("Удалить: 2");
                System.out.println("Назад: 0");
                command = getCommandInt();
                switch (command) {
                    case (1):
                        addToAirport(rememberedAirport.getAirportId(), airplane.getAirplaneId());
                        break;
                    case (2):
                        removeFromAirport(rememberedAirport.getAirportId(), airplane.getAirplaneId());
                        break;
                    case (0):
                        searchResult(rememberedAirport);
                        break;
                    default:
                        incorrectCommand();
                        break;
                }

        }

    }

    public void addToAirport(Long airportId, Long airplaneId) {
        log.debug("Menu: добавить самолет");
        System.out.println("Добавить");
        try {
            new LocationOfAirplanesService().addLink(airportId, airplaneId);
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при добавлении LocationOfAirplanes в хранилище");
            System.out.println("Не удалось добавить самолет");
            searchResult(rememberedAirport);
        }
        System.out.println("Самолет добавлен");
        searchResult(rememberedAirport);
    }

    public void removeFromAirport(Long airportId, Long airplaneId) {
        log.debug("Menu: удалить самолет");
        System.out.println("Удалить");
        try {
            new LocationOfAirplanesService().removeLink(airportId, airplaneId);
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при удалении LocationOfAirplanes в хранилище");
            System.out.println("Не удалось удалить самолет");
            searchResult(rememberedAirport);
        }
        System.out.println("Самолет удален");
        searchResult(rememberedAirport);
    }

    public void delete(int type, Long id) {
        log.debug("Menu: удалить");
        System.out.println("Удалить");
        switch (type) {
            case (1):
                try {
                    new AirplanesService().remove(id);
                } catch (EntityNotFoundException e) {
                    log.error("Ошибка при удалении Airplane");
                    System.out.println("Не удалось удалить самолет");
                    break;
                }
                System.out.println("Самолет удален");
                break;
            case (2):
                try {
                    new AirportsService().remove(id);
                } catch (EntityNotFoundException e) {
                    log.error("Ошибка при удалении Airport");
                    System.out.println("Не удалось удалить аэропорт");
                    break;
                }
                System.out.println("Аэропорт удален");
        }
    }

    public void update(int type, Long id) {
        log.debug("Menu: изменить");
        System.out.println("Изменить данные");
        switch (type) {
            case (1): {
               try{
                new AirplanesService().update((Airplane) inputFields(type, id));
                } catch (EntityNotFoundException e) {
                    log.error("не найдено Airplane c id = " + id, e);
                    System.out.println("Самолетов с таким ID не найдено");
                }
                System.out.println("Самолет изменен");
                try {
                    searchResult(new AirplanesService().getById(id));
                } catch (EntityNotFoundException e) {
                    log.error("не найдено Airplane c id = " + id, e);
                    System.out.println("Самолетов с таким ID не найдено");
                }
                break;
            }
            case (2): {
                try {
                    new AirportsService().update((Airport) inputFields(type, id));
                } catch (EntityNotFoundException e) {
                    log.error("Ошибка при изменении Airport");
                    System.out.println("Не удалось изменить Аэропорт");
                    searchResult(rememberedAirport);
                }
                System.out.println("Аэропорт изменен");
                try {
                    searchResult(new AirportsService().getById(id));
                } catch (EntityNotFoundException e) {
                    log.error("не найдено Airport c id = " + id, e);
                    System.out.println("Аэропортов с таким ID не найдено");
                }
            }
        }
    }

    public void searchResult(Airport airport) {
        log.debug("Menu: аэропорт");
        System.out.println("Аэропорт");
        System.out.println("Город: " + airport.getCity());
        System.out.println("Код IATA: " + airport.getCodeIATA());
        System.out.println("Пропускная способность: " + airport.getThroughput());
        System.out.println("Действия с аэропортом");
        System.out.println("Удалить: 1");
        System.out.println("Изменить: 2");
        System.out.println("Добавть или удалить самолет: 3");
        System.out.println("Найти все самолеты этого аэропорта: 4");
        System.out.println("Назад: 0");
        int command = getCommandInt();
        switch (command) {
            case (1):
                delete(2, airport.getAirportId());
                break;
            case (2):
                update(2, airport.getAirportId());
                break;
            case (3):
                from = 2;
                rememberedAirport = airport;
                find(1);
                break;
            case (4):
                rememberedAirport = airport;
                try {
                    searchResultsForAirport(1, new AirportsService().getAirplanesInAirport(airport.getAirportId()));
                } catch (EntityNotFoundException e) {
                    log.error("не найден самолетов привязанных к аэропорту c id = " + airport.getAirportId(), e);
                    System.out.println("Не найдено самолетов привязанных к этому аэропорту");
                }
                break;
            case (0):
                airports();
                break;
            default:
                incorrectCommand();
                break;
        }
    }

    public void getAll(int type) {
        log.debug("Menu: список всех");
        System.out.println("Список всех");
        int i = 0;
        switch (type) {
            case (1): {
                try {
                    printResult(type, new AirplanesService().getAll());
                } catch (EntityNotFoundException e) {
                    log.error("список самолетов пуст", e);
                    System.out.println("Список самолетов пуст");
                }
                break;
            }
            case (2): {
                try {
                    printResult(type, new AirportsService().getAll());
                } catch (EntityNotFoundException e) {
                    log.error("список аэропортов пуст", e);
                    System.out.println("Список аэропортов пуст");
                }

            }
        }
    }

    public String getCommandString() {
        String command;
        while (true) {
            command = new Scanner(System.in).nextLine();
            if (!command.equals("")) {
                break;
            }
            System.out.println("Ошибка: Команда не была введена.");
        }
        return command;
    }

    public int getCommandInt() {
        try {
            return Integer.parseInt(getCommandString());
        } catch (NumberFormatException e) {
            log.error("неверный формат ввода", e);
            System.out.println("Ошибка: Неверный формат ввода");
            return getCommandInt();
        }
    }

    public Long getCommandLong() {
        try {
            return Long.parseLong(getCommandString());
        } catch (NumberFormatException e) {
            log.error("неверный формат ввода", e);
            System.out.println("Ошибка: Неверный формат ввода");
            return getCommandLong();
        }
    }
}
