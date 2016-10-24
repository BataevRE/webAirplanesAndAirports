package ru.neoflex.courses14.menu;

import ru.neoflex.courses14.Application;

public interface Menu {

    abstract void render(boolean bool);
    abstract void click(String command);
    abstract void addObserver(Application app);
}
