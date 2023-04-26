import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUDapp {

    private final String URLDB;
    private final String USERNAMEDB;
    private final String PASSWORDDB;

    private static Connection connection;
    static Scanner scanner = new Scanner(System.in);

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

    private static void insertAVehicle() throws SQLException {

        String brandInput = null;
        int yearInput = 0, mileageInput = 0;
        Vehicle vehicle = new Vehicle(brandInput, yearInput, mileageInput);
        System.out.println("Enter vehicles brand name: ");
        brandInput = scanner.nextLine();
        System.out.println("Enter vehicles year of registration: ");
        yearInput = scanner.nextInt();
        System.out.println("Enter vehicles mileage: ");
        mileageInput = scanner.nextInt();
        vehicle.setVehicleBrand(brandInput);
        vehicle.setVehicleYear(yearInput);
        vehicle.setVehicleMileage(mileageInput);
        boolean successful = isVehicleInserted(vehicle);
        if (successful)
            System.out.println("Vehicle inserted successfully!");
        else
            System.out.println("ERROR");
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

    private static void printVehicles() throws SQLException {
        List<Vehicle> vehicleList = listAllVehicles();
        for (Vehicle vehicle : vehicleList)
            System.out.println(vehicle);
    }

    private static boolean isVehicleMileageUpdated(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("update vehicles set mileage = ? where id = ?");
        preparedStatement.setLong(1, vehicle.getVehicleMileage());
        preparedStatement.setLong(2, vehicle.getVehicleID());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    public static boolean isVehicleUpdated(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("update vehicles set brand = ? , year = ? , mileage = ? where id = ?");
        preparedStatement.setString(1, vehicle.getVehicleBrand());
        preparedStatement.setLong(2, vehicle.getVehicleYear());
        preparedStatement.setLong(3,vehicle.getVehicleMileage());
        preparedStatement.setLong(4,vehicle.getVehicleID());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void updateMileage() throws SQLException {

        int idInput = 0, newMileageInput = 0;
        Vehicle vehicle = new Vehicle(idInput, newMileageInput);
        System.out.println("Enter vehicle ID to update the mileage: ");
        idInput = scanner.nextInt();
        System.out.println("Enter new mileage: ");
        newMileageInput = scanner.nextInt();
        vehicle.setVehicleID(idInput);
        vehicle.setVehicleMileage(newMileageInput);
        boolean successful = isVehicleMileageUpdated(vehicle);
        if (successful)
            System.out.println("Vehicle updated successfully!");
        else
            System.out.println("ERROR");
    }

    public static boolean isVehicleDeleted(Vehicle vehicle) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from vehicles where id = ?");
        preparedStatement.setLong(1, vehicle.getVehicleID());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void deleteAVehicle() throws SQLException {

        int idKB = 0;
        Vehicle vehicle = new Vehicle(idKB);
        System.out.println("Enter vehicle ID to delete from list: ");
        idKB = scanner.nextInt();
        vehicle.setVehicleID(idKB);
        boolean successful = isVehicleDeleted(vehicle);
        if (successful)
            System.out.println("Vehicle deleted successfully!");
        else
            System.out.println("ERROR");
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

    private static boolean isUserInserted(User user) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(username,password) values (?,?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void insertAUser() throws SQLException {

        String usernameInput = null;
        String passwordInput = null;
        User user = new User(usernameInput, passwordInput);
        System.out.println("Creating a new user.");
        System.out.println("Enter a new username: ");
        usernameInput = scanner.nextLine();
        System.out.println("Enter password for " + usernameInput + " user: ");
        passwordInput = scanner.nextLine();
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        boolean successful = isUserInserted(user);
        if (successful)
            System.out.println("New user added successfully!");
        else
            System.out.println("ERROR");
    }

    private static List<User> listAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();

        connectToDB();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users order by id asc ");

        while (resultSet.next()) {
            String username = resultSet.getString("username").trim();
            String password = resultSet.getString("password").trim();
            int id = resultSet.getInt("id");
            User user = new User(username, password);
            user.setId(id);
            userList.add(user);
        }
        return userList;
    }

    private static void printUsers() throws SQLException {
        List<User> userList = listAllUsers();
        for (User user : userList)
            System.out.println(user);
    }

    private static boolean isUserPasswordUpdated(User user) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where username = ?");
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getUsername());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void updateAUserPassword() throws SQLException {

        String usernameInput, passwordInput;

        System.out.println("Enter new password for username: ");
        usernameInput = scanner.nextLine();
        System.out.println("Enter the new password for " + usernameInput + " user: ");
        passwordInput = scanner.nextLine();
        User user = new User(usernameInput, passwordInput);
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        boolean successful = isUserPasswordUpdated(user);
        if (successful)
            System.out.println("Password changed successfully for " + usernameInput + " user!");
        else
            System.out.println("ERROR");
    }

    private static boolean isMyPasswordUpdated(User oldPassword, User newPassword, int id) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where password = ? and id = ?");
        preparedStatement.setString(1, newPassword.getPassword());
        preparedStatement.setString(2, oldPassword.getPassword());
        preparedStatement.setInt(3, id);
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void updateMyPassword(int id) throws SQLException {

        System.out.println("Enter your current password : ");
        String oldPassword = scanner.nextLine();
        System.out.println("Enter a new password : ");
        String newPassword = scanner.nextLine();
        User oldPass = new User(oldPassword);
        User newPass = new User(newPassword);
        boolean successful = isMyPasswordUpdated(oldPass, newPass, id);
        if (successful)
            System.out.println("You changed your password successfully !");
        else
            System.out.println("ERROR");

    }

    private static boolean isUserDeleted(User user) throws SQLException {

        connectToDB();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where username = ? and password = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        int value = preparedStatement.executeUpdate();

        return value != 0;
    }

    private static void deleteAUser() throws SQLException {

        String usernameInput, passwordInput;

        System.out.println("Deleting user.");
        System.out.println("Enter username to delete: ");
        usernameInput = scanner.nextLine();
        System.out.println("Enter the password for " + usernameInput + " user: ");
        passwordInput = scanner.nextLine();
        User user = new User(usernameInput, passwordInput);
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        boolean successful = isUserDeleted(user);
        if (successful)
            System.out.println("User deleted successfully !");
        else
            System.out.println("ERROR");

    }

    public static int login(User user) throws SQLException {

        int id = -1;

        final String URLDB = "jdbc:postgresql://localhost:5432/finaljavaproject";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "itdomi";
        Connection connection = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username=? and password=?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            id = rs.getInt("id");
            return id;
        }
        return id;
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


    private static int printAdminUserMenu() {
        System.out.println("Admin user menu :");
        System.out.println("(1)  Insert new user.");
        System.out.println("(2)  Print all users.");
        System.out.println("(3)  Update an existent user's password.");
        System.out.println("(4)  Delete a user.");
        System.out.println("Vehicle administration menu :");
        System.out.println("(5)  Insert a new vehicles.");
        System.out.println("(6)  Print vehicles list.");
        System.out.println("(7)  Update vehicles mileage.");
        System.out.println("(8)  Delete vehicle.");
        System.out.println("(-1)  EXIT");
        return new Scanner(System.in).nextInt();
    }

    private static int printStandardUserMenu() {
        System.out.println("Vehicle administration menu :");
        System.out.println("(1)  Print vehicles list.");
        System.out.println("(2)  Update vehicles mileage.");
        System.out.println("(3)  Update my password.");
        System.out.println("(-1)  EXIT");
        return new Scanner(System.in).nextInt();
    }

    private static void adminUserMenu() throws SQLException {

        boolean enter = true;

        System.out.println("--- WELCOME ADMIN ---");

        while (enter) {
            int x = 0;
            while (x != -1) {
                x = printAdminUserMenu();
                switch (x) {
                    case 1 -> insertAUser();
                    case 2 -> printUsers();
                    case 3 -> updateAUserPassword();
                    case 4 -> deleteAUser();
                    case 5 -> insertAVehicle();
                    case 6 -> printVehicles();
                    case 7 -> updateMileage();
                    case 8 -> deleteAVehicle();
                    case -1 -> enter = false;
                    default -> System.out.println("Menu is not existing! Please try again.");
                }
            }
            break;
        }


    }

    private static void standardUserMenu(int id) throws SQLException {

        boolean enter = true;

        System.out.println("--- WELCOME ---");

        while (enter) {
            int x = 0;
            while (x != -1) {
                x = printStandardUserMenu();
                switch (x) {
                    case 1 -> printVehicles();
                    case 2 -> updateMileage();
                    case 3 -> updateMyPassword(id);
                    case -1 -> enter = false;
                    default -> System.out.println("Menu is not existing! Please try again.");
                }
            }
            break;
        }

    }


    public static void doLogin() throws SQLException, ClassNotFoundException {

        int id;
        String usernameInput, passwordInput;
        User user;

        while (true) {
            System.out.println("Username: ");
            usernameInput = scanner.nextLine();
            System.out.println("Password: ");
            passwordInput = scanner.nextLine();
            user = new User(usernameInput, passwordInput);
            id = login(user);
            user.setId(id);
            if (id != 0)
                break;
        }

        System.out.println("You logged in successfully " + usernameInput + " !");

        while (true) {
            boolean isAdmin = isAdmin(user);
            if (isAdmin)
                adminUserMenu();
            else
                standardUserMenu(id);
            break;
        }
    }


}
