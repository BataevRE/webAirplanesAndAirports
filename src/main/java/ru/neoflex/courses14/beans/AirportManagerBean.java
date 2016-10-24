package ru.neoflex.courses14.beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class AirportManagerBean implements AirportManager {
    private static final Logger log = LogManager.getLogger(AirportManagerBean.class);

    @EJB
    EntityServiceLocal entityService;
    private Airport airport;
    private boolean airportExist;
    private HashMap<Long, Airplane> oldAirplanes;
    private HashMap<Long, Airplane> airplanes;
    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public AirportManagerBean() {
    }

    @PostConstruct
    public void initAirportManagerBean() {
        airplanes = new HashMap<Long, Airplane>();
        oldAirplanes = new HashMap<Long, Airplane>();
    }

    public void setAirport(String airportId, String city, String codeIATA, String throughput) {
        if (airportId == null) {
            airportExist = false;
            airport = new Airport(new Random().nextLong(), city, codeIATA, throughput);
            log.debug("new Airport created: " + city +" "+ codeIATA);
        } else {
            airportExist = true;
            airport = new Airport(Long.parseLong(airportId), city, codeIATA, throughput);
            log.debug("updated fields of Airport: " + airportId+" " +city+" "+codeIATA);
            setAirplanesInAirport();
        }
    }

    private void setAirplanesInAirport() {
        if (airplanes.isEmpty() && oldAirplanes.isEmpty()) {
            for (Airplane airplane : em.find(Airport.class, airport.getAirportId()).getAirplanes()) {
                airplanes.put(airplane.getAirplaneId(), airplane);
                oldAirplanes.put(airplane.getAirplaneId(), airplane);
            }
        }
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(String airportId) {
        airportExist = true;
        airport = em.find(Airport.class, Long.parseLong(airportId));
        setAirplanesInAirport();
    }

    public void addAirplane(String airplaneId) {
        if (airplaneId != null) {
            Airplane airplane = em.find(Airplane.class, Long.parseLong(airplaneId));
            airplanes.put(airplane.getAirplaneId(), airplane);
        }
    }

    public void deleteAirplane(String airplaneId) {
        if (airplaneId != null) {
            airplanes.remove(Long.parseLong(airplaneId));
        }
    }

    public List<Airplane> getAirplanes() {
        List<Airplane> result = new ArrayList<Airplane>();
        result.addAll(airplanes.values());
        log.debug("number of airplane in airport: " + result.size());
        return result;
    }

    public void saveAirport() {
        entityService.saveAirport(airport);
        deleteRemovedAirplanes();
        saveAddedAirplanes();
        log.debug("Airport saved: " + airport.getAirportId()+" " +airport.getCity());
        end();
    }

    private void saveAddedAirplanes() {
        for (Airplane airplane : airplanes.values()) {
            if (!oldAirplanes.containsKey(airplane.getAirplaneId())) {
                entityService.addLocationOfAirplanes(
                        airport.getAirportId().toString(), airplane.getAirplaneId().toString());
            }
        }
    }

    private void deleteRemovedAirplanes() {
        for (Airplane airplane : oldAirplanes.values()) {
            if (!airplanes.containsKey(airplane.getAirplaneId())) {
                entityService.removeLocationOfAirplanes(
                        airport.getAirportId().toString(), airplane.getAirplaneId().toString());
            }
        }
    }

    public void end() {
        airport = null;
        airportExist = false;
        airplanes.clear();
        oldAirplanes.clear();
    }
}
