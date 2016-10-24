package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddOrRemoveAirplane extends SubMenu {
    private static final Logger log = LogManager.getLogger(AddOrRemoveAirplane.class);

    public AddOrRemoveAirplane() {
        title = "Добавить или удалить самолет";
    }

    @Override
    public void render(boolean bool) {
        System.out.println(title);
        if (bool) {
            log.debug("Menu: Добавить или удалить самолет");
            System.out.println("1: Искать самолет для добавления/удаления");
        }
    }
}
