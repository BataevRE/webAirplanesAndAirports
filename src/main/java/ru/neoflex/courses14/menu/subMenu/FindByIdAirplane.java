package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.services.AirplanesService;

public class FindByIdAirplane extends SubMenu {
    private static final Logger log = LogManager.getLogger(FindByIdAirplane.class);

    public FindByIdAirplane() {
        title = "Поиск самолета по ID";
    }

    @Override
    public void render(boolean bool) {
        super.render(false);
        if (bool) {
            log.debug("Menu: Поиск по id");
            System.out.println("Введите ID");
        }
    }

    @Override
    public void click(String command) {
        try {
            Airplane airplane = new AirplanesService().getById(Long.parseLong(command));
            app.sessionPut("airplane", airplane.getAirplaneId());
            app.setMenu(menuList.get(0));
        } catch (EntityNotFoundException e) {
            log.error("Не найдено Airplane с id = " + command, e);
            System.out.println("Самолетов с таким ID не найдено");
        } catch (NumberFormatException e) {
            log.error("неверный формат ввода", e);
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }
}
