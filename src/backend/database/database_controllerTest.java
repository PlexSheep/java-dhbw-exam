package backend.database;

import backend.accounts.DebitAccount;
import backend.people.Client;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import backend.Utils.Authentication;
import backend.accounts.Account;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class database_controllerTest {

    static Connection conn = null;

    @Test
    void saveAccount() {
        Account a = new DebitAccount(new Client("Herbert", new Date(1), "a", "a", "123"));
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
}