package ru.neoflex.courses14.jpaEntity;//To change this template use File | Settings | File Templates.


import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "AIRPORTS")

@NamedQueries({
        @NamedQuery(name = Airport.QUERY_FIND_ALL, query = "Select a from Airport a"),
        @NamedQuery(name = Airport.QUERY_FIND_BY_CITY, query = "select a from Airport a where a.city = :city"),
        @NamedQuery(name = Airport.QUERY_FIND_BY_CODE_IATA, query = "select a from Airport a where a.codeIATA = :codeIATA"),
        @NamedQuery(name = Airport.QUERY_FIND_BY_THROUGHPUT, query = "select a from Airport a where a.throughput = :throughput")})
public class Airport implements Serializable {
    public static final String QUERY_FIND_ALL = "Airport.findAll";
    public static final String QUERY_FIND_BY_CITY = "Airport.findByCity";
    public static final String QUERY_FIND_BY_CODE_IATA = "Airport.findByCodeIATA";
    public static final String QUERY_FIND_BY_THROUGHPUT = "Airport.findByThroughput";

    @Id
    private Long airportId;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String codeIATA;
    @Column(nullable = false)
    private String throughput;
    @OneToMany(mappedBy = "airport",cascade = CascadeType.MERGE)
    @JoinTable(
            name="LocationsOfAirplanes",
            joinColumns=@JoinColumn(name="airportId"),
            inverseJoinColumns={@JoinColumn(name="airplaneId")})

    private Set<Airplane> airplanes;

    public Airport() {
    }

    public Airport(Long airportId, String city, String codeIATA, String throughput) {
        this.setAirportId(airportId);
        this.setCity(city);
        this.setCodeIATA(codeIATA);
        this.setThroughput(throughput);
    }

    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void removeAirplane(Airplane airplane){
        airplanes.remove(airplane);
    }

    public void setAirplane(Airplane airplane){
        airplanes.add(airplane);
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodeIATA() {
        return codeIATA;
    }

    public void setCodeIATA(String codeIATA) {
        this.codeIATA = codeIATA;
    }

    public String getThroughput() {
        return throughput;
    }

    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }
}
