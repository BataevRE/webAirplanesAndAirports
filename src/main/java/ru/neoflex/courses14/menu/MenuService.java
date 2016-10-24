package ru.neoflex.courses14.menu;

import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;

import java.util.Scanner;

public class MenuService {

    public String getCommandString() {
        String command;
        while ((command = new Scanner(System.in).nextLine()).equals("")) {
            System.out.println("Ошибка: Команда не была введена.");
        }
        return command;
    }

    public Airplane inputAirplaneFields(Long airplaneId) {
        System.out.println("Введите бортовой номер");
        String serialNumber = getCommandString();
        System.out.println("Введите модель");
        String model = getCommandString();
        System.out.println("Введите место назначения");
        String destination = getCommandString();
        System.out.println("Введите дату выпуска");
        String releaseDate = getCommandString();
        System.out.println("Введите компания");
        String operator = getCommandString();
        return new Airplane(airplaneId, serialNumber, model, destination, releaseDate, operator);
    }

    public Airport inputAirportFields(Long airportId) {
        System.out.println("Введите город");
        String city = getCommandString();
        System.out.println("Введите код IATA");
        String codeIATA = getCommandString();
        System.out.println("Введите пропускную способность");
        String throughput = getCommandString();
        return new Airport(airportId, city, codeIATA, throughput);
    }
}
