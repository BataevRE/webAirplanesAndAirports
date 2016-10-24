package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.services.AirportsService;

public class FindByIdAirport extends FindByIdAirplane {
    private static final Logger log = LogManager.getLogger(FindByIdAirport.class);

    public FindByIdAirport(){
        title = "Поиск аэропорта по ID";
    }

    @Override
    public void click(String command) {
        try {
            Airport airport = new AirportsService().getById(Long.parseLong(command));
            app.sessionPut("airport", airport.getAirportId());
            app.setMenu(menuList.get(0));
        } catch (EntityNotFoundException e) {
            log.error("Не найдено Airport с id = " + command, e);
            System.out.println("Аэропортов с таким ID не найдено");
        } catch (NumberFormatException e) {
            log.error("неверный формат ввода", e);
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }
}
