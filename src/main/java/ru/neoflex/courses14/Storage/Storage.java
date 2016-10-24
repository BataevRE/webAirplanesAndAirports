package ru.neoflex.courses14.Storage;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {
    private static final Logger log = LogManager.getLogger(Storage.class);
    private static Storage instance;
    private StorageType storageType;
    private HashMap<Long, Airport> airports;
    private HashMap<Long, Airplane> airplanes;
    private HashSet<LocationOfAirplanes> locationsOfAirplanes;

    private Storage() {
        airports = new HashMap<Long, Airport>();
        airplanes = new HashMap<Long, Airplane>();
        locationsOfAirplanes = new HashSet<LocationOfAirplanes>();
        storageType = StorageType.XML;

    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addAirport(Airport airport) throws IOException {
        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Airports/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                log.debug("Airport saved in " + file.getAbsolutePath());
                new SerializationHelper().writeObject(new File(file.getAbsolutePath() + "/Airport" + airport.getAirportId() + ".bin"), airport);
            }
            break;
            case MEMORY:
                this.airports.put(airport.getAirportId(), airport);
                break;
            case XML:
                File file = new File("StorageXML/Airports/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                log.debug("Airport saved in " + file.getAbsolutePath());
                new JAXBHelper().marshal(airport, new File(file.getAbsolutePath() + "/Airport" + airport.getAirportId() + ".xml"));
                break;
            case DATABASE:
                new JDBCHelper().save(airport);
                break;
        }
    }
    public void updateAirport(Airport airport) throws IOException {
        switch (storageType) {
            case SERIALIZED_FILE: {
                addAirport(airport);
            }
            break;
            case MEMORY:
                addAirport(airport);
                break;
            case XML:
                addAirport(airport);
                break;
            case DATABASE:
                new JDBCHelper().update(airport);
                break;
        }
    }


    public void addAirplane(Airplane airplane) throws IOException {
        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Airplanes/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                new SerializationHelper().writeObject(new File(file + "/Airplane" + airplane.getAirplaneId() + ".bin"), airplane);
            }
            break;
            case MEMORY:
                this.airplanes.put(airplane.getAirplaneId(), airplane);
                break;
            case XML:
                File file = new File("StorageXML/Airplanes/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                new JAXBHelper().marshal(airplane, new File(file + "/Airplane" + airplane.getAirplaneId() + ".xml"));
                break;
            case DATABASE:
                new JDBCHelper().save(airplane);
                break;
        }
    }

    public void updateAirplane(Airplane airplane) throws IOException {
        switch (storageType) {
            case SERIALIZED_FILE: {
                addAirplane(airplane);
            }
            break;
            case MEMORY:
                addAirplane(airplane);
                break;
            case XML:
                addAirplane(airplane);
                break;
            case DATABASE:
                new JDBCHelper().update(airplane);
                break;
        }
    }

    public void addLocationOfAirplanes(LocationOfAirplanes locationOfAirplanes) throws IOException, ClassNotFoundException {
        Set<LocationOfAirplanes> location = new HashSet<LocationOfAirplanes>();
        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Location.bin");
                if (file.canRead()) {
                    location = (Set<LocationOfAirplanes>) new SerializationHelper().readObject(file);
                }
                location.add(locationOfAirplanes);
                new SerializationHelper().writeObject(file, location);
            }
            break;
            case MEMORY:
                this.locationsOfAirplanes.add(locationOfAirplanes);
                break;
            case XML:
                File file = new File("StorageXML/Location/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                new JAXBHelper().marshal(locationOfAirplanes, new File(file+"/LocationOfAirplanes"+locationOfAirplanes.getId()+".xml"));
                break;
            case DATABASE:
                new JDBCHelper().save(locationOfAirplanes);
                break;
        }
    }

    public void updateLocationOfAirplanes(LocationOfAirplanes locationOfAirplanes) throws IOException, ClassNotFoundException {
        switch (storageType) {
            case SERIALIZED_FILE: {
                addLocationOfAirplanes(locationOfAirplanes);
            }
            break;
            case MEMORY:
                addLocationOfAirplanes(locationOfAirplanes);
                break;
            case XML:
                addLocationOfAirplanes(locationOfAirplanes);
                break;
            case DATABASE:
                new JDBCHelper().update(locationOfAirplanes);
                break;
        }
    }

    public Map<Long, Airport> getAirports() throws IOException, ClassNotFoundException {
        Map<Long, Airport> result = new HashMap<Long, Airport>();
        switch (storageType) {
            case SERIALIZED_FILE: {
                File dir = new File("StorageBin/Airports/");
                log.debug("Read airports from " + dir.getAbsolutePath());
                File[] list = dir.listFiles();
                if (list != null) {
                    for (File file : list) {
                        if (file.getName().contains("Airport")) {
                            Airport airport = (Airport) new SerializationHelper().readObject(file);
                            result.put(airport.getAirportId(), airport);
                        }
                    }
                }
                log.debug("Successfull reading");
            }
            break;
            case MEMORY:
                result = (Map<Long, Airport>) airports.clone();
                break;
            case XML: {
                File dir = new File("StorageXML/Airports/");
                log.debug("Read airports from " + dir.getAbsolutePath());
                File[] list = dir.listFiles();
                if (list != null) {
                    for (File file : list) {
                        if (file.getName().contains("Airport")) {
                            Airport airport = (Airport) new JAXBHelper().unmarshal(file, Airport.class);
                            result.put(airport.getAirportId(), airport);
                        }
                    }
                }
                log.debug("Successfull reading");
            }
            break;
            case DATABASE:
                result = new JDBCHelper().readAirports();
                break;
        }
        return result;
    }

    public Map<Long, Airplane> getAirplanes() throws IOException, ClassNotFoundException {
        Map<Long, Airplane> result = new HashMap<Long, Airplane>();
        switch (storageType) {
            case SERIALIZED_FILE: {
                File dir = new File("StorageBin/Airplanes/");
                File[] list = dir.listFiles();
                if (list != null) {
                    for (File file : list) {
                        if (file.getName().contains("Airplane")) {
                            Airplane airplane = (Airplane) new SerializationHelper().readObject(file);
                            result.put(airplane.getAirplaneId(), airplane);
                        }
                    }
                }
            }
            break;
            case MEMORY:
                result = (Map<Long, Airplane>) airplanes.clone();
                break;
            case XML:
                File dir = new File("StorageXML/Airplanes/");
                File[] list = dir.listFiles();
                if (list != null) {
                    for (File file : list) {
                        if (file.getName().contains("Airplane")) {
                            Airplane airplane = (Airplane) new JAXBHelper().unmarshal(file, Airplane.class);
                            result.put(airplane.getAirplaneId(), airplane);
                        }
                    }
                }
                break;
            case DATABASE:
                result = new JDBCHelper().readAirplanes();
                break;
        }
        return result;
    }

    public Set<LocationOfAirplanes> getLocationsOfAirplanes() throws IOException, ClassNotFoundException {
        Set<LocationOfAirplanes> result = new HashSet<LocationOfAirplanes>();
        switch (storageType) {
            case SERIALIZED_FILE:
                result = (HashSet<LocationOfAirplanes>) new SerializationHelper().readObject(new File("StorageBin/Location.bin"));
                break;
            case MEMORY:
                result = (Set<LocationOfAirplanes>) locationsOfAirplanes.clone();
                break;
            case XML:
                File dir = new File("StorageXML/Location/");
                File[] list = dir.listFiles();
                if (list != null) {
                    for (File file : list) {
                        if (file.getName().contains("LocationOfAirplanes")) {
                            LocationOfAirplanes location = (LocationOfAirplanes) new JAXBHelper().unmarshal(file, LocationOfAirplanes.class);
                            result.add(location);
                        }
                    }
                }
                break;
            case DATABASE:
                result = new JDBCHelper().readLocationOfAirplanes();
                break;
        }
        return result;
    }

    public void removeAirport(Long id) {
        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Airports/Airport" + id + ".bin");
                if (!file.delete()) {
                    System.out.println("Нет доступа к файлу Airport" + id + ".bin");
                }
            }
            break;
            case MEMORY:
                airports.remove(id);
                break;
            case XML:
                File file = new File("StorageXML/Airports/Airport" + id + ".xml");
                if (!file.delete()) {
                    System.out.println("Нет доступа к файлу Airport" + id + ".xml");
                }
                break;
            case DATABASE:
                new JDBCHelper().remove("Airports", "airportId", id);
                break;
        }
    }

    public void removeAirplane(Long id) {
        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Airplanes/Airplane" + id + ".bin");
                if (!file.delete()) {
                    System.out.println("Нет доступа к файлу Airplane" + id + ".bin");
                }
            }
            break;
            case MEMORY:
                airplanes.remove(id);
                break;
            case XML:
                File file = new File("StorageXML/Airplanes/Airplane" + id + ".xml");
                if (!file.delete()) {
                    System.out.println("Нет доступа к файлу Airplane" + id + ".xml");
                }
                break;
            case DATABASE:
                new JDBCHelper().remove("Airplanes","airplaneId",id);
                break;
        }
    }

    public void removeLocationOfAirplanes(LocationOfAirplanes location) throws IOException, ClassNotFoundException {

        switch (storageType) {
            case SERIALIZED_FILE: {
                File file = new File("StorageBin/Location.bin");
                Set<LocationOfAirplanes> locations = (HashSet<LocationOfAirplanes>) new SerializationHelper().readObject(file);
                Set<LocationOfAirplanes> temp = new HashSet<LocationOfAirplanes>();
                temp.addAll(locations);
                for (LocationOfAirplanes locationOfAirplanes : temp) {
                    if (locationOfAirplanes.getAirportId().equals(location.getAirportId()) && locationOfAirplanes.getAirplaneId().equals(location.getAirplaneId())) {
                        locations.remove(locationOfAirplanes);
                    }
                }
                new SerializationHelper().writeObject(file, locations);
            }
            break;
            case MEMORY:
                locationsOfAirplanes.remove(location);
                break;
            case XML:
                File file = new File("StorageXML/Location/LocationOfAirplanes" + location.getId() + ".xml");
                if (!file.delete()) {
                    throw new IOException("Нет доступа к файлу LocationOfAirplanes" + location.getId() + ".xml");
                }
                break;
            case DATABASE:
                new JDBCHelper().remove("LocationsOfAirplanes","id",location.getId());
                break;
        }
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }
}
