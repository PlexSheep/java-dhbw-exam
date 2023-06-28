import backend.accounts.Account;
import backend.accounts.CreditAccount;
import backend.accounts.GiroAccount;
import backend.database.DatabaseController;
import backend.people.Client;
import backend.people.Employee;
import backend.people.Person;
import backend.utils.Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Main {

    static Person loggedIn = null;
    static boolean isEmployee = false;
    static JFrame frame = null;

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        // backend setup
        Authentication auth = new Authentication();
        DatabaseController.connect();


        LinkedList<Account> ACCOUNT_LIST = new LinkedList<>();
        LinkedList<Client> CLIENT_LIST = new LinkedList<>();
        ResultSet client_set = DatabaseController.readUsers(DatabaseController.TABLE_CLIENTS);
        assert client_set != null;
        while (client_set.next()) {
            try {
                /*
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
                */
                Client client = new Client(
                        client_set.getString("name"),
                        client_set.getDate("date"),
                        client_set.getString("address"),
                        client_set.getString("email"),
                        client_set.getString("phone"),
                        client_set.getInt("user_id")
                );
                ResultSet account_set = DatabaseController.loadAccounts(client);
                System.out.println(client_set);
                assert account_set != null;
                while (account_set.next()) {
                    try {
                        Account account = null;
                        switch (account_set.getString("type")) {
                            case "GIRO":
                                account = new GiroAccount(client);
                                client.addAccount(account);
                                ACCOUNT_LIST.add(account);
                                break;
                            case "DEBIT":
                                //type = AccountType.DEBIT;
                                break;
                            case "CREDIT":
                                account = new CreditAccount(client);
                                client.addAccount(account);
                                ACCOUNT_LIST.add(account);
                                break;
                            case "FIXED":
                                //type = AccountType.FIXED;
                                break;
                            default:
                                //System.out.println(String.format("Could not find Account type for %s", account_list.getString("IBAN")));
                                continue;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CLIENT_LIST.add(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LinkedList<Employee> EMPLOYEE_LIST = new LinkedList<>();
        ResultSet employee_list = DatabaseController.readUsers(DatabaseController.TABLE_EMPLOYEES);
        assert employee_list != null;
        while (employee_list.next()) {
            try {
                /*
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
                */
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

        /*
        LinkedList<Account> ACCOUNT_LIST = new LinkedList<>();
        ResultSet account_list = DatabaseController.readAccounts();
        assert account_list != null;
        while (account_list.next()) {
            try {
                Client owner = null;
                // get id of owner
                int ownerId = DatabaseController.getOwnerOfAccount(
                        account_list.getString("IBAN")).getInt("client");
                //System.out.println(String.format("ownerID:\t%d", ownerId));

                // now get the object for that user_id
                for (Client client : CLIENT_LIST) {
                    //System.out.println(String.format("%d == %d => %s", client.getId(), ownerId, client.getId() == ownerId));
                    if (client.getId() == ownerId) {
                        owner = client;
                    }
                }
                if (owner == null) {
                    //System.out.println(String.format("Could not find an owner for %s",
                    //        account_list.getString("IBAN")));
                    continue;
                }

                // next find the type of the account
                AccountType type;
                switch (account_list.getString("type")) {
                    case "GIRO":
                        type = AccountType.GIRO;
                        break;
                    case "DEBIT":
                        type = AccountType.DEBIT;
                        break;
                    case "CREDIT":
                        type = AccountType.CREDIT;
                        break;
                    case "FIXED":
                        type = AccountType.FIXED;
                        break;
                    default:
                        System.out.println(String.format("Could not find Account type for %s", account_list.getString("IBAN")));
                        continue;
                }
                Account account = owner.loadAccount(
                        type,
                        account_list.getString("IBAN"),
                        account_list.getInt("balance"),
                        account_list.getInt("debtLimit")
                );
                ACCOUNT_LIST.add(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(ACCOUNT_LIST);


         */

        // debug
        System.out.println(CLIENT_LIST);
        System.out.println(EMPLOYEE_LIST);

        //DatabaseController.saveUsers(herbert, "test", "Employee");
        System.out.println(DatabaseController.readUsers("client").getString("Name"));
        //database_controller.saveUsers(herbert, "test", "Employee");
        System.out.println(DatabaseController.readUser(1, "Employee").getString("name"));

        // frontend start

        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {"User ID:", username, "Password:", password};
        ImageIcon bankIcon = null;
        URL imgURL = Main.class.getResource("res/img/bank.png");
        if (imgURL != null) {
            bankIcon = new ImageIcon((new ImageIcon(imgURL)).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
        }

        while (loggedIn == null) {
            try {
                int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, bankIcon);
                if (option != JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
                if (Authentication.password_authentication(Integer.parseInt(username.getText()), password.getText(), "Employee")) {//check credentials here
                    for (Person p : EMPLOYEE_LIST) {
                        if (p.getId() == Integer.parseInt(username.getText())) {
                            System.out.println(p);
                            loggedIn = p;
                            isEmployee = true;
                            break;
                        }
                    }
                    System.out.println("Login successful");
                    System.out.println(loggedIn.getName());
                    if (isEmployee) AdminConsole.createAdminConsole();
                } else if (Authentication.password_authentication(Integer.parseInt(username.getText()), password.getText(), "client")) {
                    for (Person p : CLIENT_LIST) {
                        if (p.getId() == Integer.parseInt(username.getText())) {
                            loggedIn = p;
                            break;
                        }
                    }
                    System.out.println("Login successful");
                    System.out.println(loggedIn.getName());
                    frame = Gui.createGUI();
                    // the gui is running by now
                    frame.addWindowListener(new WindowAdapter() {
                        /** executes when the window is closing
                         * used to store our data back into the database
                         *
                         * @param e the event to be processed
                         */
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.out.println("Ending application, saving data");
                            for (Client client : CLIENT_LIST) {
                                try {
                                    client.save(DatabaseController.TABLE_CLIENTS);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            for (Employee employee : EMPLOYEE_LIST) {
                                try {
                                    employee.save(DatabaseController.TABLE_EMPLOYEES);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            /*
                            for (Account account : ACCOUNT_LIST) {
                                account.save();
                            }

                            */
                            super.windowClosing(e);
                        }
                    });
                } else {
                    System.out.println("login failed");
                    //username.setText("");
                    //password.setText("");
                }
            } catch (NumberFormatException nfe) {
                // just bad number input, repeat
                username.setText("");
            } catch (Exception e) {
                username.setText("");
                password.setText("");
                e.printStackTrace();
            }
        }


    }
}