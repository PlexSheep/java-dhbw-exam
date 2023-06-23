package backend.database;
import backend.people.Person;
import backend.Utils.Authentication;
import backend.accounts.Account;

import java.sql.*;

public class database_controller {
    static Connection conn = null;
    public static void connect() {

        try {
            String url = "jdbc:sqlite:src/backend/database/database.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveUsers(Person p, String password, String table) throws SQLException {
        try {
            String insert = "INSERT INTO " + table + "(name, address, email, phone, password) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getAddress());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelephoneNumber());
            stmt.setString(5, Authentication.hash_password(password));
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void saveAccount(Account a) throws SQLException {
        try {
            String insert = "INSERT INTO account (IBAN, type, balance, debtLimit) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, a.getIBAN());
            stmt.setString(2, a.getTYPE().toString());
            stmt.setDouble(3, a.getBalance());
            stmt.setDouble(4, a.getDebtLimit());
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static ResultSet readUsers(int id, String table) throws SQLException {
        try {
            String query = "SELECT * FROM " + table + " WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet auth_users(String name, String password, String table) throws SQLException {
        try {

            String query = "SELECT * FROM " + table + " WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            return stmt.executeQuery();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static String get_user_password(String name, String table) throws SQLException {
        try {

            String query = "SELECT `password` FROM " + table + " WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            return stmt.executeQuery().getString("password");
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public boolean changeBalance(int accID, double amount) throws SQLException {
        try {
            String query = "UPDATE `accounts` SET balance=? WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(1, accID);
            stmt.executeQuery();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return false;
    }

}
