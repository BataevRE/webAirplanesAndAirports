//package ru.neoflex.courses14.jpaEntity;//To change this template use File | Settings | File Templates.
//
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "LocationsOfAirplanes", uniqueConstraints = {@UniqueConstraint(columnNames = {"airportId", "airplaneId"})})
//@NamedQuery(name = LocationOfAirplanes.QUERY_FIND_ALL, query = "select l FROM LocationOfAirplanes l")
//public class LocationOfAirplanes implements Serializable {
//    public static final String QUERY_FIND_ALL = "Location.findAll";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    @OneToOne(mappedBy = "airportId")
//    private Long airportId;
//    @OneToOne(mappedBy = "airplaneId")
//    @Column(nullable = false)
//    private Long airplaneId;
//
//    public LocationOfAirplanes() {
//    }
//
//    public LocationOfAirplanes(Long airportId, Long airplaneId, Long id) {
//        this.setAirportId(airportId);
//        this.setAirplaneId(airplaneId);
//        this.setId(id);
//    }
//
//    public Long getAirportId() {
//        return airportId;
//    }
//
//    public void setAirportId(Long airportId) {
//        this.airportId = airportId;
//    }
//
//    public Long getAirplaneId() {
//        return airplaneId;
//    }
//
//    public void setAirplaneId(Long airplaneId) {
//        this.airplaneId = airplaneId;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//
//}
