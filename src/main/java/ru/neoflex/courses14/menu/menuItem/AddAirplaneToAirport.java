package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.services.LocationOfAirplanesService;

public class AddAirplaneToAirport extends MenuItem {
    private static final Logger log = LogManager.getLogger(AddAirplaneToAirport.class);

    public AddAirplaneToAirport() {
        title = "Добавить самолет в аэропорт";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Добавить самолет в аэропорт");
        if ((app.sessionGet("airport") != null) && (app.sessionGet("airplane") != null)) {
            try {
                new LocationOfAirplanesService().addLink((Long) app.sessionGet("airport"), (Long) app.sessionGet("airplane"));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при добавлении LocationOfAirplanes", e);
                System.out.println("Не удалось добавить самолет в аэропорт");
                return;
            }
            System.out.println("Самолет добавлен в аэропорт");
        } else {
            System.out.println("Не выбран аэропорт или самолет");
        }

    }
}
