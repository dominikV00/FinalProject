import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUDapp {

    private final String URLDB;
    private final String USERNAMEDB;
    private final String PASSWORDDB;
    private static Connection connection;

    public CRUDapp(String URLDB, String USERNAMEDB, String PASSWORDDB) {
        this.URLDB = URLDB;
        this.USERNAMEDB = USERNAMEDB;
        this.PASSWORDDB = PASSWORDDB;
    }

    public static void connectToDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        final String URLDB = "jdbc:postgresql://localhost:5432/finaljavaproject";
        final String USERNAMEDB = "postgres";
        final String PASSWORDDB = "itdomi";

        try {
            connection = DriverManager.getConnection(URLDB, USERNAMEDB, PASSWORDDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    static boolean isVehicleInserted(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicles(brand,year,mileage) values (?,?,?)");
        preparedStatement.setString(1, vehicle.getVehicleBrand());
        preparedStatement.setLong(2, vehicle.getVehicleYear());
        preparedStatement.setLong(3, vehicle.getVehicleMileage());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    static List<Vehicle> listAllVehicles() throws SQLException {

        List<Vehicle> vehicleList = new ArrayList<>();

        connectToDB();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from vehicles order by id asc ");

        while (resultSet.next()) {
            String brand = resultSet.getString("brand").trim();
            int year = resultSet.getInt("year");
            int mileage = resultSet.getInt("mileage");
            int id = resultSet.getInt("id");
            Vehicle vehicle = new Vehicle(brand, year, mileage);
            vehicle.setVehicleID(id);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public static boolean isVehicleUpdated(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("update vehicles set brand = ? , year = ? , mileage = ? where id = ?");
        preparedStatement.setString(1, vehicle.getVehicleBrand());
        preparedStatement.setLong(2, vehicle.getVehicleYear());
        preparedStatement.setLong(3, vehicle.getVehicleMileage());
        preparedStatement.setLong(4, vehicle.getVehicleID());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    public static boolean isVehicleDeleted(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from vehicles where id = ?");
        preparedStatement.setLong(1, vehicle.getVehicleID());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    public Vehicle getVehicleId(long id) throws SQLException {

        Vehicle vehicle = null;

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicles where id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String brand = resultSet.getString("brand");
            long year = resultSet.getLong("year");
            long mileage = resultSet.getLong("mileage");
            vehicle = new Vehicle(id, brand, year, mileage);
        }
        resultSet.close();
        return vehicle;
    }

    public static boolean isAdmin(User user) throws SQLException, ClassNotFoundException {

        boolean isAdmin = false;

        Class.forName("org.postgresql.Driver");
        final String URLDB = "jdbc:postgresql://localhost:5432/finaljavaproject";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "itdomi";
        Connection connection = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        PreparedStatement preparedStatement = connection.prepareStatement("select isadmin from users where username = ? and password = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            isAdmin = rs.getBoolean("isadmin");
            return isAdmin;
        }
        return isAdmin;
    }
}
