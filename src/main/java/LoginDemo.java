import java.sql.*;

public class LoginDemo {
    public static boolean validateLogin(User user) throws SQLException, ClassNotFoundException {

        boolean status;

        Class.forName("org.postgresql.Driver");
        final String URLDB = "jdbc:postgresql://localhost:5432/finaljavaproject";
        final String USERNAMEDB = "postgres";
        final String PASSWORDDB = "itdomi";
        Connection connection = DriverManager.getConnection(URLDB, USERNAMEDB, PASSWORDDB);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();

        status = resultSet.next();

        return status;
    }
}  