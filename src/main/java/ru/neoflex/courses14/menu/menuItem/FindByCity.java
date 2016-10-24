package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.menu.MenuService;
import ru.neoflex.courses14.services.AirportsService;

import java.util.Collection;

public class FindByCity extends MenuItem {
    private static final Logger log = LogManager.getLogger(FindByCity.class);

    public FindByCity() {
        title = "Поиск по городу";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: поиск по городу");
        System.out.println("Введите город");
        String city = new MenuService().getCommandString();
        try {
            Collection<Airport> airports = new AirportsService().getByCity(city);
            String formatAirport = "%-15s %-10s %-25s %-20s%n";
            System.out.printf(formatAirport, "Город", "Код IATA", "Пропускная способность", "ID");
            int i = 0;
            for (Airport airport : airports) {
                System.out.printf(formatAirport, ++i + ". " + airport.getCity(), airport.getCodeIATA(), airport.getThroughput(), airport.getAirportId());
            }
        } catch (EntityNotFoundException e) {
            log.error("Не найдено Airport с city=" + city, e);
            System.out.println("Результатов не найдено");
        }
    }

}
