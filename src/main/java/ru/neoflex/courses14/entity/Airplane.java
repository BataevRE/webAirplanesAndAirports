package ru.neoflex.courses14.entity;//To change this template use File | Settings | File Templates.

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "Airplane", propOrder = {"airplaneId", "serialNumber", "model", "destination", "releaseDate", "operator"})
@XmlRootElement(name = "Airplane")
public class  Airplane implements Serializable {
    private static final Logger log = LogManager.getLogger(Airplane.class);
    private Long airplaneId;
    private String serialNumber;
    private String model;
    private String destination;
    private String releaseDate;
    private String operator;

    public Airplane() {
    }

    public Airplane(Long airplaneId, String serialNumber, String model,
                    String destination, String releaseDate, String operator) {
        this.setAirplaneId(airplaneId);
        this.setSerialNumber(serialNumber);
        this.setModel(model);
        this.setDestination(destination);
        this.setReleaseDate(releaseDate);
        this.setOperator(operator);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        checkField(serialNumber);
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        checkField(model);
        this.model = model;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        checkField(destination);
        this.destination = destination;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        checkField(releaseDate);
        this.releaseDate = releaseDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        checkField(operator);
        this.operator = operator;
    }

    @XmlAttribute
    public Long getAirplaneId() {
        return this.airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        if (airplaneId == null) {
            log.error("airplaneId == null");
            throw new IllegalArgumentException();
        }
        this.airplaneId = airplaneId;
    }

    public String toString() {
        return serialNumber + " " + model + " " + destination + " " + releaseDate + " " + operator + " " + airplaneId;
    }

    private void checkField(String field) {
        if (((field == null) || field.equals(""))) {
            log.error("пустое или null поле");
            throw new IllegalArgumentException("Поле должно быть непустым и !=null");
        }
    }
}
