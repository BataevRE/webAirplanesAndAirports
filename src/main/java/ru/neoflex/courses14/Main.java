package ru.neoflex.courses14;


import ru.neoflex.courses14.Storage.Storage;
import ru.neoflex.courses14.Storage.StorageType;
import ru.neoflex.courses14.menu.Menu;

public class Main {

    public static void main(String[] args) {
        StorageType storageType = StorageType.XML;
        Storage.getInstance().setStorageType(storageType);
        new BootStrap().run(storageType);

//        OldMenu menu = new OldMenu();

        Menu menu = new MenuCreator().create();

        Application application = new Application(menu);

        while (true) {
//            menu.begin();
            application.run();
        }
    }
}




