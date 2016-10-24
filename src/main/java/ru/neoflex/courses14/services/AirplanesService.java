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

public class AirplanesService implements AirplanesServiceInterface {
    private static final Logger log = LogManager.getLogger(AirplanesService.class);

    @Override
    public List<Airplane> getAll() throws EntityNotFoundException {
        List<Airplane> airplanes = new ArrayList<Airplane>();
        try{
        airplanes.addAll(Storage.getInstance().getAirplanes().values());
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (airplanes.size() == 0) {
            throw new EntityNotFoundException();
        }
        return airplanes;
    }

    @Override
    public Airplane getById(Long id) throws EntityNotFoundException {
        Airplane result;
        try{
        result = Storage.getInstance().getAirplanes().get(id);
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
    public Airplane getBySerialNumber(String serialNumber) throws EntityNotFoundException {
        Airplane result = null;
        try{
        for (Airplane airplane : Storage.getInstance().getAirplanes().values()) {
            if (airplane.getSerialNumber().equals(serialNumber)) {
                result = airplane;
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
    public List<Airplane> getByModel(String model) throws EntityNotFoundException {
        List<Airplane> result = new ArrayList<Airplane>();
        try{
        for (Airplane airplane : Storage.getInstance().getAirplanes().values()) {
            if (airplane.getModel().equals(model)) {
                result.add(airplane);
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
    public List<Airplane> getByDestination(String destination) throws EntityNotFoundException {
        List<Airplane> result = new ArrayList<Airplane>();
        try{
        for (Airplane airplane : Storage.getInstance().getAirplanes().values()) {
            if (airplane.getDestination().equals(destination)) {
                result.add(airplane);
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
    public List<Airplane> getByReleaseDate(String releaseDate) throws EntityNotFoundException {
        List<Airplane> result = new ArrayList<Airplane>();
        try{
        for (Airplane airplane : Storage.getInstance().getAirplanes().values()) {
            if (airplane.getReleaseDate().equals(releaseDate)) {
                result.add(airplane);
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
    public List<Airplane> getByOperator(String operator) throws EntityNotFoundException {
        List<Airplane> result = new ArrayList<Airplane>();
        try{
        for (Airplane airplane : Storage.getInstance().getAirplanes().values()) {
            if (airplane.getOperator().equals(operator)) {
                result.add(airplane);
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
    public void add(Airplane airplane) throws EntityNotFoundException {
        log.info("add airplane");
        try{
        Storage.getInstance().addAirplane(airplane);
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        }
    }

    @Override
    public void update(Airplane airplane) throws EntityNotFoundException {
        log.info("update airplane");
        try{
            Storage.getInstance().updateAirplane(airplane);
        } catch (IOException e) {
            throw new EntityNotFoundException("ошибка ввода/вывода");
        }
    }

    @Override
    public void remove(Long airplaneId) throws EntityNotFoundException {
        log.info("remove airplane");
        try {
            for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
                if (location.getAirplaneId().equals(airplaneId)) {
                    Storage.getInstance().removeLocationOfAirplanes(location);
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при удалении самолета");
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при удалении самолета");
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        Storage.getInstance().removeAirplane(airplaneId);
    }

    @Override
    public Airport getAirportOfAirplane(Long airplaneId) throws EntityNotFoundException {
        Airport result = null;
        try {
            Map<Long, Airport> airports = Storage.getInstance().getAirports();
            for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
                if (location.getAirplaneId().equals(airplaneId)) {
                    result = airports.get(location.getAirportId());
                    break;
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при получении самолетов аэропорта");
            throw new EntityNotFoundException("ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при получении самолетов аэропорта");
            throw new EntityNotFoundException("ошибка соответсвия класса");
        }
        if (result == null) {
            throw new EntityNotFoundException();
        }
        return result;
    }
}
