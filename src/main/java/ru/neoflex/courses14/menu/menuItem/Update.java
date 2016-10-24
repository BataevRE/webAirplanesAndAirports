package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.menu.MenuService;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;

public class Update extends MenuItem {
    private static final Logger log = LogManager.getLogger(Update.class);

    public Update() {
        title = "Изменить данные";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Изменить данные");
        if (app.sessionGet("from").equals("airplanes")) {
            try{
            new AirplanesService().update(new MenuService().inputAirplaneFields((Long) app.sessionGet("airplane")));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при изменении данных самолета");
                System.out.println("Не удалось изменить данные");
                return;
            }
        } else {
            try {
                new AirportsService().update(new MenuService().inputAirportFields((Long) app.sessionGet("airport")));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при изменении данных аэропорта");
                System.out.println("Не удалось изменить данные");
                return;
            }
        }
        System.out.println("Данные изменены");
    }
}
