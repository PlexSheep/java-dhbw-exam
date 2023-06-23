package backend.database;

import backend.accounts.DebitAccount;
import backend.people.Client;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;

import backend.accounts.Account;

import java.sql.*;
import java.util.Date;

class databaseControllerTest {

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

    @Test
    void saveAccount() throws SQLException {
        connect();
        Client c = new Client("Herbert", new Date(1), "Here", "s", "e");
        DatabaseController.saveUsers(c, "test", "client");
        Account a = new DebitAccount(c);
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
            System.out.println("Crash");
            e.printStackTrace();
        }
    }


    @Test
    void loadAccount() {
        connect();
        Iban iban = Iban.valueOf("DE31783118194453522807");
        try {
            String query = "SELECT * FROM account WHERE IBAN = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, iban.toString());
            System.out.println(stmt.executeQuery().getString(1));
        }
        catch (Exception e){
            System.out.println(e);
        }
        //return null;
    }
}