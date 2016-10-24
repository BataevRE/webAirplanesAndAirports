package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;

public class Delete extends MenuItem {
    private static final Logger log = LogManager.getLogger(Delete.class);

    public Delete() {
        title = "Удалить";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Удалить");
        if (app.sessionGet("from").equals("airplanes")) {
            try {
                new AirplanesService().remove((Long) app.sessionGet("airplane"));
            } catch (EntityNotFoundException e) {
                System.out.println("Не удалось удалить самолет");
                return;
            }
            System.out.println("Самолет удален");
        } else {
            try {
                new AirportsService().remove((Long) app.sessionGet("airport"));
            } catch (EntityNotFoundException e) {
                System.out.println("Не удалось удалить аэропорт");
                return;
            }
            System.out.println("Аэропорт удален");
        }
        app.beginning();
    }
}