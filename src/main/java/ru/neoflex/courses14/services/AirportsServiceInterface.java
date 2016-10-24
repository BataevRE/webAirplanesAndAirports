package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;

import java.util.List;

public interface AirportsServiceInterface {

    abstract List<Airport> getAll() throws EntityNotFoundException;

    abstract Airport getById(Long id) throws EntityNotFoundException;

    abstract List<Airport> getByCity(String city) throws EntityNotFoundException;

    abstract Airport getByCodeIATA(String codeIATA) throws EntityNotFoundException;

    abstract List<Airport> getByThroughput(String throughput) throws EntityNotFoundException;

    abstract void add(Airport airport) throws EntityNotFoundException;

    abstract void update(Airport airport) throws EntityNotFoundException;

    abstract void remove(Long airportId) throws EntityNotFoundException;

    abstract List<Airplane> getAirplanesInAirport(Long airportId) throws EntityNotFoundException;
}
