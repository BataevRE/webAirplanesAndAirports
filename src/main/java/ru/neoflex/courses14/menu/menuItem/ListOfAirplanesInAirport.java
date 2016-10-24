package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.services.AirportsService;

import java.util.Collection;

public class ListOfAirplanesInAirport extends MenuItem {
    private static final Logger log = LogManager.getLogger(ListOfAirplanesInAirport.class);

    public ListOfAirplanesInAirport() {
        title = "Список самолетов в аэропорту";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Список самолетов в аэропорту");
        try {
            Collection<Airplane> airplanes = new AirportsService().getAirplanesInAirport((Long) app.sessionGet("airport"));
            String formatAirplane = "%-15s %-20s %-20s %-15s %-20s %-20s%n";
            System.out.printf(formatAirplane, "Бортовой номер", "Модель", "Место назначения", "Дата выпуска", "Компания", "ID");
            int i = 0;
            for (Airplane airplane : airplanes) {
                System.out.printf(formatAirplane, ++i + ". " + airplane.getSerialNumber(), airplane.getModel(), airplane.getDestination(), airplane.getReleaseDate(), airplane.getOperator(), airplane.getAirplaneId());
            }
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при получении списка самолетов в аэропорту", e);
            System.out.println("Список самолетов пуст");
        }
    }
}
