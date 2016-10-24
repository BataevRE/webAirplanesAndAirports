package ru.neoflex.courses14;

import ru.neoflex.courses14.menu.Menu;
import ru.neoflex.courses14.menu.MenuService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Application {
    private Map<String, Object> session;
    private LinkedList<Menu> menuStack;
    private Menu menu;

    public Application(Menu menu) {
        menuStack = new LinkedList<Menu>();
        session = new HashMap<String, Object>();
        this.menu = menu;
    }

    public Object sessionGet(String key) {
        return session.get(key);
    }

    public void sessionPut(String key, Object obj) {
        session.put(key, obj);
    }

    public void setMenu(Menu menu) {
        menuStack.addLast(this.menu);
        this.menu = menu;
    }

    public void beginning() {
        setMenu(menuStack.getFirst());
        menuStack.clear();
    }

    public void run() {
        menu.addObserver(this);
        menu.render(true);
        if (menuStack.isEmpty()) {
            System.out.println("0: Выход");
        } else {
            System.out.println("0: Назад");
        }
        String command = new MenuService().getCommandString();
        if (command.equals("0")) {
            if (menuStack.isEmpty()) {
                System.exit(0);
            } else {
                menu = menuStack.removeLast();
            }
        } else {
            menu.click(command);
        }
    }
}
