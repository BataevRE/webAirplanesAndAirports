package ru.neoflex.courses14.Storage;

import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 12.12.14
 * Time: 3:11 
 */
public class JDBCHelper {
    private static final String username = "study";
    private static final String password = "study";
    private static final String url = "jdbc:oracle:thin:@192.168.56.102:1521:orcl";
    private Connection conn;

    public JDBCHelper() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Airport airport) {
        PreparedStatement pst = null;
        String query = "insert into Airports values(?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(1, airport.getAirportId());
            pst.setString(2, airport.getCity());
            pst.setString(3, airport.getCodeIATA());
            pst.setString(4, airport.getThroughput());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void update(Airport airport) {
        PreparedStatement pst = null;
        String query = "update Airports set city=?, codeIATA=?, throughput=? where airportId=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(4, airport.getAirportId());
            pst.setString(1, airport.getCity());
            pst.setString(2, airport.getCodeIATA());
            pst.setInt(3, Integer.parseInt(airport.getThroughput()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(Airplane airplane) {
        PreparedStatement pst = null;
        String query = "insert into Airplanes values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(1, airplane.getAirplaneId());
            pst.setString(2, airplane.getSerialNumber());
            pst.setString(3, airplane.getModel());
            pst.setString(4, airplane.getDestination());
            pst.setString(5, airplane.getReleaseDate());
            pst.setString(6, airplane.getOperator());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Airplane airplane) {
        PreparedStatement pst = null;
        String query = "update Airplanes set serialNumber=?, model=?, destination=?, releaseDate=?, operator=? where airplaneId=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(6, airplane.getAirplaneId());
            pst.setString(1, airplane.getSerialNumber());
            pst.setString(2, airplane.getModel());
            pst.setString(3, airplane.getDestination());
            pst.setString(4, airplane.getReleaseDate());
            pst.setString(5, airplane.getOperator());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(LocationOfAirplanes location) {
        PreparedStatement pst = null;
        String query = "insert into LocationsOfAirplanes values(?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(1, location.getId());
            pst.setLong(2, location.getAirportId());
            pst.setLong(3, location.getAirplaneId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(LocationOfAirplanes location) {
        PreparedStatement pst = null;
        String query = "update LocationsOfAirplanes set airportId=?, airplaneId=? where id=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setLong(1, location.getAirportId());
            pst.setLong(2, location.getAirplaneId());
            pst.setLong(3, location.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Long, Airplane> readAirplanes() {
        Statement st = null;
        ResultSet rs = null;
        Map<Long, Airplane> airplanes = new HashMap<Long, Airplane>();
        String query = "select * from Airplanes";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                airplanes.put(rs.getLong(1), new Airplane(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return airplanes;
    }

    public Map<Long, Airport> readAirports() {
        Statement st = null;
        ResultSet rs = null;
        Map<Long, Airport> airports = new HashMap<Long, Airport>();
        String query = "select * from Airports";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                airports.put(rs.getLong(1), new Airport(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return airports;
    }

    public Set<LocationOfAirplanes> readLocationOfAirplanes() {
        Statement st = null;
        ResultSet rs = null;
        Set<LocationOfAirplanes> locations = new HashSet<LocationOfAirplanes>();
        String query = "select * from LocationsOfAirplanes";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                locations.add(new LocationOfAirplanes(rs.getLong(2), rs.getLong(3), rs.getLong(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return locations;
    }

    public void remove(String entityName, String idName,Long id){
        Statement st = null;
        ResultSet rs = null;
        String query = "delete from "+entityName+" where "+idName +" = "+id;
        try {
            st = conn.createStatement();
//            pst.setString(1, idName);
//            pst.setLong(2, id);
            st.execute(query);

//            if(rs.next()){
//                rs.deleteRow();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
