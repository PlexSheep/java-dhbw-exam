import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private JLabel JNameVariable;

    private JLabel JIbanVar;
    private JLabel JBicVar;
    private JButton JChangePassword;
    private JButton JChangeCredentials;
    private JButton JDeleteAccount;
    private JButton JLogout;
    public JPanel JMain;
    private JTextField JNewPassword;



    public UserData() {
        JChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == JChangePassword) {
                    try {
                        String newPassword = JNewPassword.getText();
                        Connection conn = DatabaseController.conn;
                        UserData.updatePassword(conn, newPassword);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });



        Person p = Main.loggedIn;
        String name = p.getName();
        JNameVariable.setText(name);



        JChangeCredentials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCredential.ChangeCredential();
            }
        });
    }

    public static void createUser() {
        UserData test = new UserData();
        test.setContentPane(test.JMain);
        test.setTitle("Test");
        test.setSize(500, 400);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

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
