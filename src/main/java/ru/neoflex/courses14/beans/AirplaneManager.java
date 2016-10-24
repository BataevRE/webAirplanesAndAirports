package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AirplaneManager {
    public void initAirplaneManagerBean();

    public void setAirplane(String airplaneId, String serialNumber, String model, String destination, String releaseDate, String operator);

    public Airplane getAirplane();

    public void setAirplane(String airplaneId);

    public void addAirport(String airportId);

    public void deleteAirport(String airportId);

    public List<Airport> getAirports();

    public void saveAirplane();

    public void end();
}
