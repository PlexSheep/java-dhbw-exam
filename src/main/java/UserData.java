import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.people.Client;
import backend.utils.Authentication;
import backend.people.Person;
import backend.utils.Authentication;
import backend.database.DatabaseController;


public class UserData extends JFrame {
    private JLabel JAdress;
    private JLabel JTitle;
    private JLabel JEmail;
    private JLabel JNumber;
    private JPanel JNamePanel;
    private JPanel JEmailPanel;
    private JPanel JNumberPanel;
    private JLabel JName;
    private JPanel JAdressPanel;
    private JLabel profilePicturePanel;
    private JPanel JIban;
    private JPanel JBic;
    private JLabel JAdressVariable;
    private JLabel JEmailVar;
    private JLabel JNumberVar;

    private JLabel JIBAN;
    private JLabel JBIC;

    private JLabel JNameVariable;

    private JLabel JIbanVar;
    private JLabel JBicVar;
    private JButton JChangePassword;
    private JButton JChangeCredentials;
    private JButton JDeleteAccount;
    private JButton JLogout;
    public JPanel JMain;
    private JTextField JNewPassword;


    /**
     * Function to display the users data on the GUI
     */
    public UserData() {
        JChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == JChangePassword) {
                    String newPassword = JNewPassword.getText();
                     if (DatabaseController.changePassword(Main.loggedIn, newPassword)) {
                         // success
                         // empty the text field
                         JNewPassword.setText("");
                     }
                }
            }

        });


        String columnName = "name";
        JNameVariable.setText(getColumnValue("Employee", columnName));
        String columnAdress = "address";
        JAdressVariable.setText(getColumnValue("Employee", columnAdress));

        Client p = (Client) Main.loggedIn;
        String name = p.getName();
        JNameVariable.setText(name);
        String address = p.getAddress();
        JAdressVariable.setText(address);
        String number = p.getTelephoneNumber();
        JNumberVar.setText(number);
        String mail = p.getEmail();
        JEmailVar.setText(mail);
        try {
            JIbanVar.setText(p.getAccounts().get(0).getIBAN());
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        JChangeCredentials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCredential.ChangeCredential();
            }
        });
        JLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public static void createUser() {
        UserData accountCenter = new UserData();
        accountCenter.setContentPane(accountCenter.JMain);
        accountCenter.setTitle("Konto Center");
        accountCenter.setSize(500, 400);
        accountCenter.setVisible(true);
        accountCenter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Update the password from the GUI
     * @param conn
     * @param newPassword
     * @throws SQLException
     * @deprecated do not use this, does not even work. use DatabaseController.changePassword
     */
    public static void updatePassword(Connection conn, String newPassword) throws SQLException {
        try {
            String hashedPassword = Authentication.hash_password(newPassword);

            String updateQuery = "UPDATE Employee SET password = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, hashedPassword);
            statement.setString(2, "Herbert");

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Table updated successfully.");
            } else {
                System.out.println("No rows were updated.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getColumnValue(String tableName, String columnName) {
        String value = "";

        try {
            Connection conn = DatabaseController.conn;
            String query = "SELECT " + columnName + " FROM " + tableName + " WHERE name = 'Herbert'";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                value = resultSet.getString(columnName);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }
}