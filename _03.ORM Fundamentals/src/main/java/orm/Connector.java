package orm;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;
    private static final String jdbc = "jdbc:mysql://localhost:3306/%s";

    public static void createConnection(String user, String password, String dbName) throws SQLException {
        Properties pros = new Properties();
        pros.setProperty("user", user);
        pros.setProperty("password", password);

        String formattedJdbc = String.format(jdbc, dbName);

        connection = DriverManager.getConnection(formattedJdbc, pros);
    }
    public static Connection getConnection(){
        return connection;
    }

    public static boolean isConnectionAvailable(){return connection !=null;}


}
