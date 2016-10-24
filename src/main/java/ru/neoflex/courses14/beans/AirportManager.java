package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.Local;
import javax.jws.WebService;
import java.util.List;

@Local
public interface AirportManager {

    public void initAirportManagerBean();

    public void setAirport(String airportId, String city, String codeIATA, String throughput);

    public Airport getAirport();

    public void setAirport(String airportId);

    public void addAirplane(String airplaneId);

    public void deleteAirplane(String airplaneId);

    public List<Airplane> getAirplanes();

    public void saveAirport();

    public void end();
}
