package ru.neoflex.courses14.Servlets;


import ru.neoflex.courses14.Storage.Storage;
import ru.neoflex.courses14.Storage.StorageType;
import ru.neoflex.courses14.BootStrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.Override;


@WebListener()
public class ServletBootstrap implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        StorageType storageType = StorageType.XML;
//        Storage.getInstance().setStorageType(storageType);
//        new BootStrap().run(storageType);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
