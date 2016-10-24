package ru.neoflex.courses14.menu.menuItem;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.Application;
import ru.neoflex.courses14.menu.Menu;

public class MenuItem implements Menu {
    protected String title;
    protected Application app;

    @Override
    public void addObserver(Application app) {
        this.app = app;
    }

    @Override
    public void render(boolean bool) {
        System.out.println(title);
    }

    @Override
    public void click(String command) {
        System.out.println("Выбран пункт " + title);
    }
}
