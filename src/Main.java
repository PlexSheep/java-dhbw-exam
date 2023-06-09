import backend.People.Client;
import backend.People.Person;
import backend.database.database_controller;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        database_controller.connect();
        Client herbert = new Client("Herbert", new Date(1), "Here", "s" ,"e");
        database_controller.saveUsers(herbert, "Employee");
        System.out.println(database_controller.readUsers(1, "Employee").getString("Name"));

    }
}