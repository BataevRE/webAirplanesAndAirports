package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;

import java.util.Collection;


public class ListOfAll extends MenuItem {
    private static final Logger log = LogManager.getLogger(ListOfAll.class);

    public ListOfAll() {
        title = "Список всех";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Список всех");
        if (app.sessionGet("from").equals("airplanes")) {
            try {
                Collection<Airplane> airplanes = new AirplanesService().getAll();
                String formatAirplane = "%-15s %-20s %-20s %-15s %-20s %-20s%n";
                System.out.printf(formatAirplane, "Бортовой номер", "Модель", "Место назначения", "Дата выпуска", "Компания", "ID");
                int i = 0;
                for (Airplane airplane : airplanes) {
                    System.out.printf(formatAirplane, ++i + ". " + airplane.getSerialNumber(), airplane.getModel(), airplane.getDestination(), airplane.getReleaseDate(), airplane.getOperator(), airplane.getAirplaneId());
                }
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при получении списка всех самолетов", e);
                System.out.println("Список самолетов пуст");
            }
        } else {
            try {
                Collection<Airport> airports = new AirportsService().getAll();
                String formatAirport = "%-15s %-10s %-25s %-20s%n";
                System.out.printf(formatAirport, "Город", "Код IATA", "Пропускная способность", "ID");
                int i = 0;
                for (Airport airport : airports) {
                    System.out.printf(formatAirport, ++i + ". " + airport.getCity(), airport.getCodeIATA(), airport.getThroughput(), airport.getAirportId());
                }
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при получении списка всех аэропортов", e);
                System.out.println("Список аэропортов пуст");
            }
        }
    }
}
