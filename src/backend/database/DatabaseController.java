package backend.database;
import backend.accounts.CreditAccount;
import backend.people.Client;
import backend.people.Person;
import backend.Utils.Authentication;
import backend.accounts.Account;
import org.iban4j.Iban;

import java.sql.*;
import java.util.Date;

public class DatabaseController {

    public static final String TABLE_CLIENTS = "client";
    public static final String TABLE_EMPLOYEES = "Employee";

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

    public static void fillDb() throws SQLException {
        for(int i = 0; i < 1000; i++){
            Client dave = new Client("dave", new Date(1), "Here", "s", "e");
            saveUsers(dave, "test", "client");

            Account a = new CreditAccount(dave);
            saveAccount(dave, a);
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
            e.printStackTrace();
        }
    }

    public static void updateUsers(Person p, String password, String table) throws SQLException {
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
            e.printStackTrace();
        }
    }

    public static void saveAccount(Client c, Account a) throws SQLException {
        try {
            String insert = "INSERT INTO account (IBAN, type, balance, debtLimit) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, a.getIBAN());
            stmt.setString(2, a.getTYPE().toString());
            stmt.setDouble(3, a.getBalance());
            stmt.setDouble(4, a.getDebtLimit());
            stmt.executeUpdate();

            insert = "INSERT INTO client_account (client, account) VALUES(?, ?)";
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, c.getId());
            stmt.setString(2, a.getIBAN());
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void updateAccount(Account a) throws SQLException {
        try {
            String insert = "UPDATE account set (IBAN, type, balance, debtLimit) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, a.getIBAN());
            stmt.setString(2, a.getTYPE().toString());
            stmt.setDouble(3, a.getBalance());
            stmt.setDouble(4, a.getDebtLimit());
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static ResultSet loadAccount(Iban iban) throws SQLException {
        try {
            String query = "SELECT * FROM account WHERE IBAN = ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, iban.toString());
            return stmt.executeQuery();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static ResultSet readUsers(String table) throws SQLException {
        String query = "SELECT * FROM " + table;
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }


    /**
     * get a single user from the database
     *
     * @param id  the id of the user
     * @param table should be one of the constants: TABLE_CLIENTS, TABLE_EMPLOYEES
     * @return
     * @throws SQLException
     */
    public static ResultSet readUser(int id, String table) throws SQLException {
        String query = "SELECT * FROM " + table + " WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeQuery();
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
