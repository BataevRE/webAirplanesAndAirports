package ru.neoflex.courses14;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.Storage.JDBCHelper;
import ru.neoflex.courses14.Storage.StorageType;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.services.AirplanesService;
import ru.neoflex.courses14.services.AirportsService;
import ru.neoflex.courses14.services.LocationOfAirplanesService;

import java.io.File;
import java.util.Random;

public class BootStrap {
    private static final Logger log = LogManager.getLogger(BootStrap.class);

    public void run(StorageType storageType){
        File file;
        switch (storageType) {
            case SERIALIZED_FILE:
                file = new File("StorageBin");
                if (!file.exists()) {
                    run();
                }
                break;
            case XML:
                file = new File("StorageXML");
                if (!file.exists()) {
                    run();
                }
                break;
            case MEMORY:
                run();
                break;
            case DATABASE:
                if(new JDBCHelper().readAirports().isEmpty()){
                    run();
                }
                break;
        }
    }

    public void run() {
        AirportsService airportsService = new AirportsService();
        AirplanesService airplanesService = new AirplanesService();
        LocationOfAirplanesService locationOfAirplanesService = new LocationOfAirplanesService();

        Random random = new Random();
        try {
            airportsService.add(new Airport(random.nextLong(), "Москва", "DME", "30000000"));
            airportsService.add(new Airport(random.nextLong(), "Москва", "SVO", "30000000"));
            airportsService.add(new Airport(random.nextLong(), "Москва", "VKO", "10000000"));
            airportsService.add(new Airport(random.nextLong(), "Саратов", "RTW", "500000"));
            airportsService.add(new Airport(random.nextLong(), "Мурманск", "MMK", "600000"));
        } catch (EntityNotFoundException e) {
            System.out.println("Не удалось создать аэропорты");
            log.error("Ошибка при создании аэропортов");
        }
        try{
        airplanesService.add(new Airplane(random.nextLong(), "RA-42370", "Як-42Д", "Красноярск", "1989", "КрасАвиа"));
        airplanesService.add(new Airplane(random.nextLong(), "VQ-BRY", "Embraer ERJ-195SR", "Барселона", "2008", "СарАвиа"));
        airplanesService.add(new Airplane(random.nextLong(), "RA-47697", "Ан-24РВ", "Брянск", "1972", "Псков-Авиа"));
        airplanesService.add(new Airplane(random.nextLong(), "VP-BYO", "Boeing 737-500", "Ханты-Мансийск", "1998", "Трансаэро - ТСО"));
        airplanesService.add(new Airplane(random.nextLong(), "RA-64051", "Ту-204С(Е)", "Хабаровск", "2010", "Трансаэро - ТСО"));
        } catch (EntityNotFoundException e) {
            System.out.println("Не удалось создать самолеты");
            log.error("Ошибка при создании самолетов");
        }

        try {
            locationOfAirplanesService.addLink(airportsService.getByCity("Москва").get(0).getAirportId(), airplanesService.getByDestination("Брянск").get(0).getAirplaneId());
            locationOfAirplanesService.addLink(airportsService.getByCity("Москва").get(1).getAirportId(), airplanesService.getBySerialNumber("RA-42370").getAirplaneId());
            locationOfAirplanesService.addLink(airportsService.getByCity("Москва").get(2).getAirportId(), airplanesService.getBySerialNumber("VQ-BRY").getAirplaneId());
            locationOfAirplanesService.addLink(airportsService.getByCodeIATA("RTW").getAirportId(), airplanesService.getBySerialNumber("VP-BYO").getAirplaneId());
            locationOfAirplanesService.addLink(airportsService.getByCodeIATA("RTW").getAirportId(), airplanesService.getBySerialNumber("RA-64051").getAirplaneId());
        } catch (EntityNotFoundException e) {
            System.out.println("Не удалось создать связи");
            log.error("Не удалось создать связи", e);
        }
    }
}
