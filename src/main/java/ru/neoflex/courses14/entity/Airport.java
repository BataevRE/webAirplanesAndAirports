package ru.neoflex.courses14.entity;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "Airport", propOrder = {"airportId","city","codeIATA","throughput"})
@XmlRootElement(name = "Airport")
public class Airport implements Serializable{
    private static final Logger log = LogManager.getLogger(Airport.class);
    private Long airportId;
    private String city;
    private String codeIATA;
    private String throughput;

    public Airport() {
    }

    public Airport(Long airportId, String city, String codeIATA, String throughput) {
        this.setAirportId(airportId);
        this.setCity(city);
        this.setCodeIATA(codeIATA);
        this.setThroughput(throughput);
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        if(airportId == null){
            log.error("airportId == null");
            throw new IllegalArgumentException();
        }
        this.airportId = airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        checkField(city);
        this.city = city;
    }

    public String getCodeIATA() {
        return codeIATA;
    }

    public void setCodeIATA(String codeIATA) {
        checkField(codeIATA);
        this.codeIATA = codeIATA;
    }

    public String getThroughput() {
        return throughput;
    }

    public void setThroughput(String throughput) {
        checkField(throughput);
        this.throughput = throughput;
    }

    public String toString() {
        return getCity() + " " + getCodeIATA() + " " + getThroughput() + " " + getAirportId().toString();
    }

    private void checkField(String field) {
        if ((field == null) || (field.equals(""))) {
            log.error("пустое или null поле");
            throw new IllegalArgumentException("Поле не должно быть пустым");
        }
    }
}
