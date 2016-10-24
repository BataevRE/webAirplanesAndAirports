package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Airports extends SubMenu {
    public Airports() {
        title = "Аэропорты";
    }

    @Override
    public void render(boolean bool) {
        super.render(bool);
        if (bool) {
            app.sessionPut("from", "airports");
        }
    }
}


