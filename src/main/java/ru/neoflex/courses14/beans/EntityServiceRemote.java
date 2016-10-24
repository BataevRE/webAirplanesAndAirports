package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;


@Remote
@WebService(name = "entityServicePortType")
public interface EntityServiceRemote {
    @WebMethod
    public void saveAirport(@WebParam(name = "airport") Airport airport);

    @WebMethod
    public void saveAirplane(@WebParam(name = "airplane") Airplane airplane);

    @WebMethod
    public List<Airport> getAllAirport();

    @WebMethod
    public List<Airplane> getAllAirplane();

    @WebMethod
    public void addLocationOfAirplanes(@WebParam(name = "airportId") String airportId, @WebParam(name = "airplaneId") String airplaneId);

    @WebMethod
    public void removeAirport(@WebParam(name = "airportId") String airportId);

    @WebMethod
    public void removeAirplane(@WebParam(name = "airplaneId") String airplaneId);

    @WebMethod
    public void removeLocationOfAirplanes(@WebParam(name = "airportId") String airportId, @WebParam(name = "airplaneId") String airplaneId);

    @WebMethod
    public List<Airplane> findAirplanes(@WebParam(name = "fieldForSearch") String fieldForSearch, @WebParam(name = "textForSearch") String textForSearch);

    @WebMethod
    public List<Airport> findAirports(@WebParam(name = "fieldForSearch") String fieldForSearch, @WebParam(name = "textForSearch") String textForSearch);
}
