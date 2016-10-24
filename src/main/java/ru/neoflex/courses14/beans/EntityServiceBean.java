package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Stateless
@WebService(serviceName = "EntityService",
        portName = "EntityServicePort",
        endpointInterface = "ru.neoflex.courses14.beans.EntityServiceRemote")
public class EntityServiceBean implements EntityServiceLocal, EntityServiceRemote {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public EntityServiceBean() {
    }

    public void saveAirplane(Airplane airplane) {
//        em.getTransaction().begin();
        em.merge(airplane);
//        em.getTransaction().commit();
    }

    public void saveAirport(Airport airport) {
//        em.getTransaction().begin();
        em.merge(airport);
//        em.getTransaction().commit();
    }

    public List<Airport> getAllAirport() {
        return em.createNamedQuery(Airport.QUERY_FIND_ALL, Airport.class).getResultList();
    }

    public List<Airplane> getAllAirplane() {
        return em.createNamedQuery(Airplane.QUERY_FIND_ALL, Airplane.class).getResultList();
    }

    public void addLocationOfAirplanes(String airportId, String airplaneId) {
        Airport airport = em.find(Airport.class, Long.parseLong(airportId));
        Airplane airplane = em.find(Airplane.class, Long.parseLong(airplaneId));
//        em.getTransaction().begin();
        airplane.setAirport(airport);
        airport.setAirplane(airplane);
        em.merge(airport);
        em.merge(airplane);
//        em.getTransaction().commit();
    }

    public void removeAirport(String airportId) {
        Long id = Long.parseLong(airportId);
//        em.getTransaction().begin();
        em.remove(em.find(Airport.class, id));
//        em.getTransaction().commit();
    }

    public void removeAirplane(String airplaneId) {
        Long id = Long.parseLong(airplaneId);
//        em.getTransaction().begin();
        em.remove(em.find(Airplane.class, id));
//        em.getTransaction().commit();
    }

    public void removeLocationOfAirplanes(String airportId, String airplaneId) {
        Airport airport = em.find(Airport.class, Long.parseLong(airportId));
        Airplane airplane = em.find(Airplane.class, Long.parseLong(airplaneId));
//        em.getTransaction().begin();
        airport.removeAirplane(airplane);
        airplane.setAirport(null);
        em.merge(airport);
        em.merge(airplane);
//        em.getTransaction().commit();
    }

    public List<Airplane> findAirplanes(String fieldForSearch, String textForSearch) {
        List<Airplane> airplanes = new ArrayList<Airplane>();
        switch (fieldForSearch) {
            case "airplaneId":
                airplanes.add(em.find(Airplane.class, Long.parseLong(textForSearch)));
                break;
            case "serialNumber":
                airplanes.addAll(em.createNamedQuery(Airplane.QUERY_FIND_BY_SERIAL_NUMBER, Airplane.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList());
                break;
            case "model":
                airplanes = em.createNamedQuery(Airplane.QUERY_FIND_BY_MODEL, Airplane.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
            case "destination":
                airplanes = em.createNamedQuery(Airplane.QUERY_FIND_BY_DESTINATION, Airplane.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
            case "releaseDate":
                airplanes = em.createNamedQuery(Airplane.QUERY_FIND_BY_RELEASE_DATE, Airplane.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
            case "operator":
                airplanes = em.createNamedQuery(Airplane.QUERY_FIND_BY_OPERATOR, Airplane.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
        }
        if (airplanes.size() == 0) {
            airplanes = null;
        }
        return airplanes;
    }

    public List<Airport> findAirports(String fieldForSearch, String textForSearch) {
        List<Airport> airports = new ArrayList<Airport>();
        switch (fieldForSearch) {
            case "airportId":
                airports.add(em.find(Airport.class, Long.parseLong(textForSearch)));
                break;
            case "codeIATA":
                airports.addAll(em.createNamedQuery(Airport.QUERY_FIND_BY_CODE_IATA, Airport.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList());
                break;
            case "city":
                airports = em.createNamedQuery(Airport.QUERY_FIND_BY_CITY, Airport.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
            case "throughput":
                airports = em.createNamedQuery(Airport.QUERY_FIND_BY_THROUGHPUT, Airport.class)
                        .setParameter(fieldForSearch, textForSearch).getResultList();
                break;
        }
        if (airports.size() == 0) {
            airports = null;
        }
        return airports;
    }
}
