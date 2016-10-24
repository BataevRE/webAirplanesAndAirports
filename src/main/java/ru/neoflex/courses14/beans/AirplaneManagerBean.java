package ru.neoflex.courses14.beans;

import ru.neoflex.courses14.jpaEntity.Airplane;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Stateful
public class AirplaneManagerBean implements AirplaneManager {

    @EJB
    EntityServiceLocal entityService;
    private Airplane airplane;
    private boolean airplaneExist;
    private HashMap<Long, Airport> oldAirports;
    private HashMap<Long, Airport> airports;
    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public AirplaneManagerBean() {
    }

    @PostConstruct
    public void initAirplaneManagerBean() {
        airports = new HashMap<Long, Airport>();
        oldAirports = new HashMap<Long, Airport>();
    }

    @Override
    public void setAirplane(String airplaneId, String serialNumber, String model, String destination, String releaseDate, String operator) {
        if (airplaneId == null) {
            airplaneExist = false;
            airplane = new Airplane(new Random().nextLong(), serialNumber, model, destination, releaseDate, operator);
        } else {
            airplaneExist = true;
            airplane = new Airplane(Long.parseLong(airplaneId), serialNumber, model, destination, releaseDate, operator);
            setAirportsOfAirplane();
        }
    }

    private void setAirportsOfAirplane() {
        if (airports.isEmpty() && oldAirports.isEmpty()) {
            Airport airport = em.find(Airplane.class, airplane.getAirplaneId()).getAirport();
            if(airport != null){
            airports.put(airport.getAirportId(), airport);
            oldAirports.put(airport.getAirportId(), airport);
            }
        }
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplaneId) {
        airplaneExist = true;
        airplane = em.find(Airplane.class, Long.parseLong(airplaneId));
        setAirportsOfAirplane();
    }

    public void addAirport(String airportId) {
        if (airportId != null) {
            Airport airport = em.find(Airport.class, Long.parseLong(airportId));
            airports.put(airport.getAirportId(), airport);
        }
    }

    public void deleteAirport(String airportId) {
        if (airportId != null) {
            airports.remove(Long.parseLong(airportId));
        }
    }

    public List<Airport> getAirports() {
        List<Airport> result = new ArrayList<Airport>();
        result.addAll(airports.values());
        return result;
    }

    public void saveAirplane() {
        entityService.saveAirplane(airplane);
        deleteRemovedAirports();
        saveAddedAirports();
        end();
    }

    private void saveAddedAirports() {
        for (Airport airport : airports.values()) {
            if (!oldAirports.containsKey(airport.getAirportId())) {
                entityService.addLocationOfAirplanes(
                        airport.getAirportId().toString(), airplane.getAirplaneId().toString());
            }
        }

    }

    private void deleteRemovedAirports() {
        for (Airport airport : oldAirports.values()) {
            if (!airports.containsKey(airport.getAirportId())) {
                entityService.removeLocationOfAirplanes(
                        airport.getAirportId().toString(), airplane.getAirplaneId().toString());
            }
        }
    }

    public void end() {
        airplane = null;
        airplaneExist = false;
        initAirplaneManagerBean();
    }
}
