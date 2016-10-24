package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.services.AirplanesService;

public class AirplaneMenu extends SubMenu {
    private static final Logger log = LogManager.getLogger(AirplaneMenu.class);

    public AirplaneMenu() {
        title = "Самолет";
    }

    @Override
    public void render(boolean bool) {
        try {
            Airplane airplane = new AirplanesService().getById((Long) app.sessionGet("airplane"));
            System.out.println("Бортовой номер: " + airplane.getSerialNumber());
            System.out.println("Модель: " + airplane.getModel());
            System.out.println("Место назначения: " + airplane.getDestination());
            System.out.println("Дата выпуска: " + airplane.getReleaseDate());
            System.out.println("Компания: " + airplane.getOperator());
        } catch (EntityNotFoundException e) {
            log.error("Ошибка при поиске Airplane по id", e);
            System.out.println("Самолет не найден");
            return;
        }
        super.render(bool);
    }
}
