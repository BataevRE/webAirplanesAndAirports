package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.Storage.Storage;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirportsService implements AirportsServiceInterface {
    private static final Logger log = LogManager.getLogger(AirportsService.class);

    @Override
    public List<Airport> getAll() throws EntityNotFoundException {
        List<Airport> airports = new ArrayList<Airport>();
        try {
            airports.addAll(Storage.getInstance().getAirports().values());
        } catch (IOException e) {
            log.error("Ошибка при получении списка всех аэропортов");
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при получении списка всех аэропортов");
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (airports.size() == 0) {
            throw new EntityNotFoundException();
        }
        return airports;
    }

    @Override
    public Airport getById(Long id) throws EntityNotFoundException {
        Airport airport;
        try {
            airport = Storage.getInstance().getAirports().get(id);
        } catch (IOException e) {
            log.error("Ошибка при получении аэропорта по id");
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при получении аэропорта по id");
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (airport == null) {
            throw new EntityNotFoundException();
        }
        return airport;
    }

    @Override
    public List<Airport> getByCity(String city) throws EntityNotFoundException {
        List<Airport> result = new ArrayList<Airport>();
        try {
            for (Airport airport : Storage.getInstance().getAirports().values()) {
                if (airport.getCity().equals(city)) {
                    result.add(airport);
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при получении аэропорта по городу");
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при получении аэропорта по городу");
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException("Не найдено Airport c city = "+city);
        }
        return result;
    }

    @Override
    public Airport getByCodeIATA(String codeIATA) throws EntityNotFoundException {
        Airport result = null;
        try {
            for (Airport airport : Storage.getInstance().getAirports().values()) {
                if (airport.getCodeIATA().equals(codeIATA)) {
                    result = airport;
                    break;
                }
            }
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (result == null) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public List<Airport> getByThroughput(String throughput) throws EntityNotFoundException {
        List<Airport> result = new ArrayList<Airport>();
        try {
            for (Airport airport : Storage.getInstance().getAirports().values()) {
                if (airport.getThroughput().equals(throughput)) {
                    result.add(airport);
                }
            }
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public void add(Airport airport) throws EntityNotFoundException {
        log.info("add Airport");
        try {
            Storage.getInstance().addAirport(airport);
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        }
    }

    @Override
    public void update(Airport airport) throws EntityNotFoundException {
        log.info("update Airport");
        try {
            Storage.getInstance().updateAirport(airport);
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        }
    }

    @Override
    public void remove(Long airportId) throws EntityNotFoundException {
        log.info("remove Airport");
        try {
            for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
                if (location.getAirportId().equals(airportId)) {
                    Storage.getInstance().removeLocationOfAirplanes(location);
                }
            }
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        Storage.getInstance().removeAirport(airportId);
    }

    @Override
    public List<Airplane> getAirplanesInAirport(Long airportId) throws EntityNotFoundException {
        List<Airplane> result = new ArrayList<Airplane>();
        try {
            Map<Long, Airplane> airplanes = Storage.getInstance().getAirplanes();
            for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
                if (location.getAirportId().equals(airportId)) {
                    result.add(airplanes.get(location.getAirplaneId()));
                }
            }
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException();
        }
        return result;
    }
}
