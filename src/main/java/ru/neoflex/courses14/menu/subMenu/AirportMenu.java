package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.services.AirportsService;

public class AirportMenu extends SubMenu {
    private static final Logger log = LogManager.getLogger(AirportMenu.class);

    public AirportMenu() {
        title = "Аэропорт";
    }

    @Override
    public void render(boolean bool) {
        try {
            Airport airport = new AirportsService().getById((Long) app.sessionGet("airport"));
            System.out.println("Город: " + airport.getCity());
            System.out.println("Код IATA: " + airport.getCodeIATA());
            System.out.println("Пропускная способность: " + airport.getThroughput());
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при поиске Airport по id", e);
            System.out.println("Аэропорт не найден");
            return;
        }
        super.render(bool);
    }
}
