package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database_controller {
    private String url = "jdbc:sqlite:";

    Connection conn =  DriverManager.getConnection(url);

    public database_controller() throws SQLException {
    }
}
