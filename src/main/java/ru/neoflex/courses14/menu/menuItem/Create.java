package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.menu.MenuService;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;

import java.util.Random;


public class Create extends MenuItem {
    private static final Logger log = LogManager.getLogger(Create.class);

    public Create() {
        title = "Создать";
    }

    @Override
    public void click(String command) {
        log.debug("Menu: Создать");
        if (app.sessionGet("from").equals("airplanes")) {
            try{
            new AirplanesService().add(new MenuService().inputAirplaneFields(new Random().nextLong()));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при создании Airplane");
                System.out.println("Не удалось создать самолет");
            }
        } else {
            try {
                new AirportsService().add(new MenuService().inputAirportFields(new Random().nextLong()));
            } catch (EntityNotFoundException e) {
                log.error("Ошибка при создании Airport");
                System.out.println("Не удалось создать аэропорт");
            }
        }
    }
}
