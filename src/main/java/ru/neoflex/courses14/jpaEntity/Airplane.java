package ru.neoflex.courses14.jpaEntity;//To change this template use File | Settings | File Templates.

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Table(name = "Airplanes")
@NamedQueries({
        @NamedQuery(name = Airplane.QUERY_FIND_ALL, query = "Select a from Airplane a"),
        @NamedQuery(name = Airplane.QUERY_FIND_BY_SERIAL_NUMBER, query = "select a from Airplane a where a.serialNumber = :serialNumber"),
        @NamedQuery(name = Airplane.QUERY_FIND_BY_MODEL, query = "select a from Airplane a where a.model = :model"),
        @NamedQuery(name = Airplane.QUERY_FIND_BY_DESTINATION, query = "select a from Airplane a where a.destination= :destination"),
        @NamedQuery(name = Airplane.QUERY_FIND_BY_RELEASE_DATE, query = "select a from Airplane a where a.releaseDate = :releaseDate"),
        @NamedQuery(name = Airplane.QUERY_FIND_BY_OPERATOR, query = "select a from Airplane a where a.operator = :operator")})
public class Airplane implements Serializable {
    public static final String QUERY_FIND_ALL = "Airplane.findAll";
    public static final String QUERY_FIND_BY_SERIAL_NUMBER = "Airplane.findBySerialNumber";
    public static final String QUERY_FIND_BY_MODEL = "Airplane.findByModel";
    public static final String QUERY_FIND_BY_DESTINATION = "Airplane.findByDestination";
    public static final String QUERY_FIND_BY_RELEASE_DATE = "Airplane.findByReleaseDate";
    public static final String QUERY_FIND_BY_OPERATOR = "Airplane.findByOperator";

    @Id
    private Long airplaneId;
    @Column(nullable = false)
    private String serialNumber;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String releaseDate;
    @Column(nullable = false)
    private String operator;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(
            name="LocationsOfAirplanes",
            joinColumns=@JoinColumn(name="airplaneId"),
            inverseJoinColumns={@JoinColumn(name="airportId")})

    private Airport airport;

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

    @XmlTransient
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getAirplaneId() {
        return this.airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

}
