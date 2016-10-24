package ru.neoflex.courses14;

import ru.neoflex.courses14.menu.Menu;
import ru.neoflex.courses14.menu.menuItem.*;
import ru.neoflex.courses14.menu.subMenu.*;

public class MenuCreator {

    public Menu create(){
        SubMenu menuBegin = new Begin();
        SubMenu airplanes = new Airplanes();
        SubMenu airports = new Airports();
        SubMenu findAirplanes = new Find();
        SubMenu findAirplanes2 = new Find();
        SubMenu findAirports = new Find();
        SubMenu findByIdAirplanes = new FindByIdAirplane();
        SubMenu findByIdAirplanes2 = new FindByIdAirplane();
        SubMenu findByIdAirports = new FindByIdAirport();
        SubMenu airplaneMenu = new AirplaneMenu();
        SubMenu airplaneMenu2 = new AirplaneMenu();
        SubMenu airportMenu = new AirportMenu();
        SubMenu addOrRemoveAirplane = new AddOrRemoveAirplane();

        addOrRemoveAirplane.addMenu(findAirplanes2);
        findAirplanes2.addMenu(new FindByDestination());
        findAirplanes2.addMenu(findByIdAirplanes2);
        findByIdAirplanes2.addMenu(airplaneMenu2);
        airplaneMenu2.addMenu(new AddAirplaneToAirport());
        airplaneMenu2.addMenu(new RemoveAirplaneFromAirport());

        airplaneMenu.addMenu(new Delete());
        airplaneMenu.addMenu(new Update());

        airportMenu.addMenu(new Delete());
        airportMenu.addMenu(new Update());
        airportMenu.addMenu(new ListOfAirplanesInAirport());
        airportMenu.addMenu(addOrRemoveAirplane);

        findByIdAirplanes.addMenu(airplaneMenu);
        findByIdAirports.addMenu(airportMenu);

        findAirplanes.addMenu(new FindByDestination());
        findAirplanes.addMenu(findByIdAirplanes);

        findAirports.addMenu(new FindByCity());
        findAirports.addMenu(findByIdAirports);

        airplanes.addMenu(new Create());
        airplanes.addMenu(new ListOfAll());
        airplanes.addMenu(findAirplanes);

        airports.addMenu(new Create());
        airports.addMenu(new ListOfAll());
        airports.addMenu(findAirports);

        menuBegin.addMenu(airplanes);
        menuBegin.addMenu(airports);
        return menuBegin;
    }
}
