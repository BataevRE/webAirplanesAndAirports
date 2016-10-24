package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.menu.MenuService;
import ru.neoflex.courses14.services.AirplanesService;

import java.util.Collection;

public class FindByDestination extends MenuItem {
    private static final Logger log = LogManager.getLogger(FindByDestination.class);

    public FindByDestination() {
        title = "Поиск по местоназначению";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: поиск по месту назначения");
        System.out.println("Введите место назначения");
        String destination = new MenuService().getCommandString();
        try {
            Collection<Airplane> airplanes = new AirplanesService().getByDestination(destination);
            String formatAirplane = "%-15s %-20s %-20s %-15s %-20s %-20s%n";
            System.out.printf(formatAirplane, "Бортовой номер", "Модель", "Место назначения", "Дата выпуска", "Компания", "ID");
            int i = 0;
            for (Airplane airplane : airplanes) {
                System.out.printf(formatAirplane, ++i + ". " + airplane.getSerialNumber(), airplane.getModel(), airplane.getDestination(), airplane.getReleaseDate(), airplane.getOperator(), airplane.getAirplaneId());
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Результатов не найдено");
            log.error("не найдено Airplane c destination=" + destination, e);
        }
    }
}
