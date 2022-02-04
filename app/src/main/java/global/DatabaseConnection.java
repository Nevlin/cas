package global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection con = null;

    static {
        String username = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASS");
        String dbName = "cas";
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
