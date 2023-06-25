package backend.database;

import backend.Utils.Authentication;
import backend.accounts.CreditAccount;
import backend.accounts.DebitAccount;
import backend.people.Client;
import backend.people.Person;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;

import backend.accounts.Account;

import javax.sql.RowSet;
import java.sql.*;
import java.util.Date;

class databaseControllerTest {

    static Connection conn = null;

    static Client dave = new Client("dave", new Date(1), "Here", "s", "e");
    static Account a = new CreditAccount(dave);

    @Test
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
    void fillDb() throws SQLException {
        DatabaseController.connect();
        for(int i = 0; i < 200; i++){
            Client dave = new Client("dave", new Date(1), "Here", "s", "e");
            DatabaseController.saveUsers(dave, "test", "client");

            Account a = new CreditAccount(dave);
            DatabaseController.saveAccount(dave, a);
        }
    }

    @Test
    void testReadTransaction() throws SQLException {
        DatabaseController.connect();
        ResultSet res = DatabaseController.readTransactionBySender(dave);
        while(res.next()) {
            System.out.println(res.getString("amount"));
        }
    }

    @Test
    void testTransactionTable() throws SQLException {
        DatabaseController.connect();
        DatabaseController.saveTransaction(dave, dave, 1000.88);
    }

}