import backend.people.Client;
import backend.people.Employee;
import backend.people.Person;
import backend.utils.Authentication;
import backend.database.DatabaseController;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.net.URL;
import javax.swing.*;
import java.awt.Image;
import java.util.LinkedList;

public class Main {

    static Person loggedIn;

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        Authentication auth = new Authentication();
        DatabaseController.connect();
        LinkedList<Client> CLIENT_LIST = new LinkedList<>();
        ResultSet client_set = DatabaseController.readUsers(DatabaseController.TABLE_CLIENTS);
        System.out.println(client_set);
        assert client_set != null;
        while (client_set.next()) {
            try {
                System.out.println(
                        String.format(
                                "id:\t\t\t%s\n" +
                                        "name:\t\t%s\n" +
                                        "address:\t%s\n" +
                                        "email:\t\t%s\n" +
                                        "telephone:\t%s\n" +
                                        "pass:\t\t%s\n" +
                                        "date:\t\t%s\n",
                                client_set.getString("ID"),          // index 1
                                client_set.getString("name"),        // index 2
                                client_set.getString("address"),     // index 3
                                client_set.getString("email"),       // index 4
                                client_set.getString("phone"),       // index 5
                                client_set.getString("password"),    // index 6
                                client_set.getString("date")         // index 7
                        )
                );
                Client client = new Client(
                        client_set.getString("name"),
                        client_set.getDate("date"),
                        client_set.getString("address"),
                        client_set.getString("email"),
                        client_set.getString("phone"),
                        client_set.getInt("user_id")
                );
                CLIENT_LIST.add(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LinkedList<Employee> EMPLOYEE_LIST = new LinkedList<>();
        ResultSet employee_list = DatabaseController.readUsers(DatabaseController.TABLE_EMPLOYEES);
        System.out.println(employee_list);
        assert employee_list != null;
        while (employee_list.next()) {
            try {
                System.out.println(
                        String.format(
                                "id:\t\t\t%s\n" +
                                        "name:\t\t%s\n" +
                                        "address:\t%s\n" +
                                        "email:\t\t%s\n" +
                                        "telephone:\t%s\n" +
                                        "pass:\t\t%s\n" +
                                        "date:\t\t%s\n",
                                employee_list.getString("ID"),          // index 1
                                employee_list.getString("name"),        // index 2
                                employee_list.getString("address"),     // index 3
                                employee_list.getString("email"),       // index 4
                                employee_list.getString("phone"),       // index 5
                                employee_list.getString("password"),    // index 6
                                employee_list.getString("date")         // index 7
                        )
                );
                Employee employee = new Employee(
                        employee_list.getString("name"),
                        employee_list.getDate("date"),
                        employee_list.getString("address"),
                        employee_list.getString("email"),
                        employee_list.getString("phone"),
                        employee_list.getInt("user_id")
                );
                EMPLOYEE_LIST.add(employee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // debug
        System.out.println(CLIENT_LIST);
        System.out.println(EMPLOYEE_LIST);


        Client herbert = new Client("Herbert", new Date(1), "Here", "s", "e");
        //herbert.login("FFF");

        //DatabaseController.saveUsers(herbert, "test", "Employee");
        System.out.println(DatabaseController.readUsers("client").getString("Name"));
        //database_controller.saveUsers(herbert, "test", "Employee");
        System.out.println(DatabaseController.readUser(1, "Employee").getString("name"));

        // frontend start

        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {"User ID:", username, "Password:", password};
        ImageIcon bankIcon = null;
        URL imgURL = Main.class.getResource("amogus.png");
        if (imgURL != null) {
            bankIcon = new ImageIcon((new ImageIcon(imgURL)).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
        }
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, bankIcon);
        if (option != JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
                if (auth.password_authentication(Integer.parseInt(username.getText()), password.getText(), "Employee")) {//check credentials here
                    for (Person p : EMPLOYEE_LIST) {
                        if (p.getId() == Integer.parseInt(username.getText())) {
                            System.out.println(p);
                            loggedIn = p;
                            break;
                        }
                    }
                    System.out.println("Login successful");
                    System.out.println(loggedIn.getName());
                    Gui.createGUI();
                }
                else if (auth.password_authentication(Integer.parseInt(username.getText()), password.getText(), "client")){
                        for (Person p : CLIENT_LIST) {
                            if (p.getId() == Integer.parseInt(username.getText())) {
                                loggedIn = p;
                                break;
                            }
                        }
                    System.out.println("Login successful");
                    System.out.println(loggedIn.getName());
                    Gui.createGUI();
                }

                else {
                    System.out.println("login failed");
                    //maybe repeat here
                }
        }
    }
}