package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.Local;
import java.util.List;


@Local
public interface EntityServiceLocal {

    public void saveAirport(Airport airport);

    public void saveAirplane(Airplane airplane);

    public List<Airport> getAllAirport();

    public List<Airplane> getAllAirplane();

    public void addLocationOfAirplanes(String airportId, String airplaneId);

    public void removeAirport(String airportId);

    public void removeAirplane(String airplaneId);

    public void removeLocationOfAirplanes(String airportId, String airplaneId);

    public List<Airplane> findAirplanes(String fieldForSearch, String textForSearch);

    public List<Airport> findAirports(String fieldForSearch, String textForSearch);
}
