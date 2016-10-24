package ru.neoflex.courses14.menu.subMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.Application;
import ru.neoflex.courses14.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class SubMenu implements Menu {
    private static final Logger log = LogManager.getLogger(SubMenu.class);
    protected Application app;
    protected String title;
    protected List<Menu> menuList = new ArrayList<Menu>();

    public void addMenu(Menu menu) {
        menuList.add(menu);
    }

    public void addObserver(Application app) {
        this.app = app;
    }

    @Override
    public void render(boolean bool) {
        System.out.println(title);
        if (bool) {
            log.debug("Menu: " + title);
            bool = false;
            for (int i = 0; i < menuList.size(); i++) {
                System.out.print(i + 1 + ": ");
                menuList.get(i).render(bool);
            }
        }
    }

    @Override
    public void click(String command) {
        Menu menu;
        try {
            menu = menuList.get(Integer.parseInt(command) - 1);
        } catch (NumberFormatException e) {
            log.error("Ошибка при попытке привести " + command + " к Integer", e);
            System.out.println("Неверная команда");
            return;
        }
        if (menu instanceof SubMenu) {
            app.setMenu(menu);
        } else {
            menu.addObserver(app);
            menu.render(false);
            menu.click(command);
        }
    }
}
