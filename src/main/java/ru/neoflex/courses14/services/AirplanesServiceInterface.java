package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;

import java.util.List;

public interface AirplanesServiceInterface {
    abstract List<Airplane> getAll() throws EntityNotFoundException;

    abstract Airplane getById(Long id) throws EntityNotFoundException;

    abstract Airplane getBySerialNumber(String serialNumber) throws EntityNotFoundException;

    abstract List<Airplane> getByModel(String model) throws EntityNotFoundException;

    abstract List<Airplane> getByDestination(String destination) throws EntityNotFoundException;

    abstract List<Airplane> getByReleaseDate(String releaseDate) throws EntityNotFoundException;

    abstract List<Airplane> getByOperator(String operator) throws EntityNotFoundException;

    abstract void add(Airplane airplane) throws EntityNotFoundException;

    abstract void update(Airplane airplane) throws EntityNotFoundException;

    abstract void remove(Long airplaneId) throws EntityNotFoundException;

    abstract Airport getAirportOfAirplane(Long airplaneId) throws EntityNotFoundException;
}
