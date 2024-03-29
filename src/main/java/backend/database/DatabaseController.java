package backend.database;

import backend.accounts.Account;
import backend.people.Client;
import backend.people.Employee;
import backend.people.Person;
import backend.utils.Authentication;

import java.sql.*;
import java.util.Date;

public class DatabaseController {

    public static final String TABLE_CLIENTS = "client";
    public static final String TABLE_EMPLOYEES = "Employee";
    public static final String TABLE_ACCOUNTS = "account";
    public static final String TABLE_CLIENT_ACCOUNTS = "client_account";
    public static Connection conn = null;

    public static void connect() {
        try {
            String url = "jdbc:sqlite:src/main/java/backend/database/database.db";
            conn = DriverManager.getConnection(url);

            //System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initially save a user
     *
     * @param p
     * @param password
     * @param table
     * @throws SQLException
     */
    public static void saveUsers(Person p, String password, String table) throws SQLException {
        try {
            String insert = "INSERT INTO " + table + "(user_id, name, address, email, phone, password, date) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getAddress());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getTelephoneNumber());
            stmt.setString(6, Authentication.hash_password(password));
            stmt.setString(7, p.getBirthday().toString());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void updateUsers(Person p, String table) throws SQLException {
        try {
            String insert = "UPDATE " + table + " set name = ?, address = ?, email = ?, phone = ? WHERE user_id = ?";
            //System.out.println(String.format("prepared query:\t%s", insert));
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getAddress());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelephoneNumber());
            stmt.setInt(5, p.getId());
            //System.out.println(String.format("query:\t%s", stmt.toString()));
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Initially save a transaction
     *
     * @param sender
     * @param iban
     * @param amount
     * @throws SQLException
     */
    public static void saveTransaction(Client sender, String iban, Double amount) throws SQLException {
        try {
            String insert = "INSERT INTO transactions (sender, recipient, amount, timestamp) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setInt(1, sender.getId());
            stmt.setString(2, iban);
            stmt.setDouble(3, amount);
            stmt.setString(4, new Date().toString());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Read transactions for specific sender
     *
     * @param p
     * @throws SQLException
     */
    public static ResultSet readTransactionByClient(Person p) throws SQLException {
        try {
            String query = "SELECT * FROM transactions WHERE sender=? OR recipient=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, p.getId());
            stmt.setInt(1, p.getId());
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read transactions for specific recipient
     *
     * @param recipient
     * @throws SQLException
     */
    public static ResultSet readTransactionBySRecipient(Client recipient) throws SQLException {
        try {
            String insert = "SELECT * FROM transactions WHERE sender=?";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setInt(1, recipient.getId());
            stmt.setString(3, new Date().toString());
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet readTransactions() throws SQLException {
        try {
            String query = "SELECT * FROM transactions";
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initially save an account to the database
     *
     * @param c
     * @param a
     * @throws SQLException
     */
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
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void updateAccount(Account a) throws SQLException {
        try {
            String insert = "UPDATE account set type = ?, balance = ?, debtLimit = ?  WHERE IBAN =?";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, a.getTYPE().toString());
            stmt.setDouble(2, a.getBalance());
            stmt.setDouble(3, -a.getDebtLimit());
            stmt.setString(4, a.getIBAN());

            System.out.println(String.format("query:\t%s", stmt.toString()));
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static ResultSet loadAccounts(Person p) throws SQLException {
        try {
            String query = "SELECT * FROM client, account, client_account WHERE client.user_id = ? AND client.user_id = client_account.client AND account.IBAN = client_account.account";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, p.getId());
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Get all users from the database
     *
     * @param table
     * @return
     * @throws SQLException
     */
    public static ResultSet readUsers(String table) throws SQLException {
        String query = "SELECT * FROM " + table;
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }

    public static ResultSet readAccounts() throws SQLException {
        String query = "SELECT * FROM " + DatabaseController.TABLE_ACCOUNTS;
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
        String query = "SELECT * FROM " + table + " WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeQuery();
    }

    public static ResultSet authUsers(String name, String password, String table) throws SQLException {
        try {

            String query = "SELECT * FROM " + table + " WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Function to get user password from the database
     *
     * @param userId
     * @param table
     * @return
     * @throws SQLException
     */
    public static String getUserPassword(Integer userId, String table) throws SQLException {
        try {
            String query = "SELECT password FROM " + table + " WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            return stmt.executeQuery().getString("password");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getOwnerOfAccount(String iban) throws SQLException {
        try {
            String query = "SELECT * FROM  " + TABLE_CLIENT_ACCOUNTS + " WHERE account=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, iban);
            stmt.setMaxRows(1);
            return stmt.executeQuery();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static boolean changeBalance(int accID, double amount) throws SQLException {
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

    public static boolean changePassword(Person p, String pass) {
        try {
            String table = null;
            if (p instanceof Client) {
                table = TABLE_CLIENTS;
            } else if (p instanceof Employee) {
                table = TABLE_EMPLOYEES;
            } else {
                System.out.printf("Invalid Person type: %s%n", p);
                return false;
            }
            String update = "UPDATE " + table + " set password = ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setString(1, Authentication.hash_password(pass));
            stmt.setInt(2, p.getId());
            stmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
