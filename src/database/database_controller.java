package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database_controller {
    private static String url = "jdbc:sqlite:database/database.db";

    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveUsers(Person p){

    }
}
