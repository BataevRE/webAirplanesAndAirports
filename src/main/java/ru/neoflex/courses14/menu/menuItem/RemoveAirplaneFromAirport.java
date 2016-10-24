package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.services.LocationOfAirplanesService;

public class RemoveAirplaneFromAirport extends MenuItem {
    private static final Logger log = LogManager.getLogger(RemoveAirplaneFromAirport.class);

    public RemoveAirplaneFromAirport() {
        title = "Удалить самолет из аэропорта";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Удалить самолет из аэропорта");
        if ((app.sessionGet("airport") != null) && (app.sessionGet("airplane") != null)) {
            try {
                new LocationOfAirplanesService().removeLink((Long) app.sessionGet("airport"), (Long) app.sessionGet("airplane"));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при удалении LocationOfAirplanes", e);
                System.out.println("Не удалось удалить самолет из аэропорта");
                return;
            }
            System.out.println("Самолет удален из аэропорта");
        } else {
            System.out.println("не выбран аэропорт или самолет");
        }
    }
}
