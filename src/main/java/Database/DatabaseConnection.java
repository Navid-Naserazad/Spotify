package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection connection;

    public Connection getDBConnection() {
        String databaseUser = "root";
        String databasePassword = "password";
        String url = "jdbc:mysql://localhost:3306/spotify";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
